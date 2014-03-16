var map, autocomplete;

		function initialize() {
			var mapProp = {
				zoom: 15,
				mapTypeControl: true,
				mapTypeControlOptions: {
					mapTypeIds: [google.maps.MapTypeId.ROADMAP, google.maps.MapTypeId.SATELLITE],
					style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
				},
			};

			map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

			// Try HTML5 geolocation
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					var pos = new google.maps.LatLng(position.coords.latitude,
						position.coords.longitude);
					map.setCenter(pos);
				}, function() {
					handleNoGeolocation(true);
				});
			} else {
				// Browser doesn't support Geolocation
				handleNoGeolocation(false);
			}

			var input = /** @type {HTMLInputElement} */ (
				document.getElementById('event-location'));
			autocomplete = new google.maps.places.Autocomplete(input);
			autocomplete.bindTo('bounds', map);

			var infowindow = new google.maps.InfoWindow();
			var marker = new google.maps.Marker({
				map: map
			});

			google.maps.event.addListener(autocomplete, 'place_changed', function() {
				infowindow.close();
				marker.setVisible(false);
				var place = autocomplete.getPlace();
				if (!place.geometry) {
					return;
				}

				// If the place has a geometry, then present it on a map.
				if (place.geometry.viewport) {
					map.fitBounds(place.geometry.viewport);
				} else {
					map.setCenter(place.geometry.location);
					map.setZoom(17); // Why 17? Because it looks good.
				}
				marker.setPosition(place.geometry.location);
				marker.setVisible(true);

				var address = '';
				if (place.address_components) {
					address = [
						(place.address_components[0] && place.address_components[0].short_name || ''), (place.address_components[1] && place.address_components[1].short_name || ''), (place.address_components[2] && place.address_components[2].short_name || '')
					].join(' ');
				}

				infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
				infowindow.open(map, marker);
			});
		}

		function handleNoGeolocation(errorFlag) {
			if (errorFlag) {
				var content = 'Error: The Geolocation service failed.';
			} else {
				var content = 'Error: Your browser doesn\'t support geolocation.';
			}

			var options = {
				map: map,
				position: new google.maps.LatLng(60, 105),
				content: content
			};

			var infowindow = new google.maps.InfoWindow(options);
			map.setCenter(options.position);
		}

		function geolocate() {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					var geolocation = new google.maps.LatLng(
						position.coords.latitude, position.coords.longitude);
					autocomplete.setBounds(new google.maps.LatLngBounds(geolocation,
						geolocation));
				});
			}
		}

		google.maps.event.addDomListener(window, 'load', initialize);