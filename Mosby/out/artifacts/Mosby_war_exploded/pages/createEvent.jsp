<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Create event - Mosby - event management</title>
    <link rel="shortcut icon" href="media/images/favicon.ico">
    <link rel="icon" type="image/png" href="media/images/favicon.png" />
    <meta name="description" content="Mosby - make it simple. New event management system" />

    <meta name="viewport" content="width=1000, initial-scale=1.0, maximum-scale=1.0">

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">
    <link href="css/pro-features.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">

    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
    <script>
        // This example displays an address form, using the autocomplete feature
        // of the Google Places API to help users fill in the information.

        var placeSearch, autocomplete;
        var componentForm = {
            street_number: 'short_name',
            route: 'long_name',
            locality: 'long_name',
            administrative_area_level_1: 'short_name',
            country: 'long_name',
            postal_code: 'short_name'
        };

        function initialize() {
            // Create the autocomplete object, restricting the search
            // to geographical location types.
            autocomplete = new google.maps.places.Autocomplete(
                    /** @type {HTMLInputElement} */
                    (document.getElementById('event-location')), {
                        types: ['geocode']
                    });
            // When the user selects an address from the dropdown,
            // populate the address fields in the form.
            google.maps.event.addListener(autocomplete, 'place_changed', function() {
                fillInAddress();
            });
        }

        // [START region_fillform]
        function fillInAddress() {
            // Get the place details from the autocomplete object.
            var place = autocomplete.getPlace();

            for (var component in componentForm) {
                document.getElementById(component).value = '';
                document.getElementById(component).disabled = false;
            }

            // Get each component of the address from the place details
            // and fill the corresponding field on the form.
            for (var i = 0; i < place.address_components.length; i++) {
                var addressType = place.address_components[i].types[0];
                if (componentForm[addressType]) {
                    var val = place.address_components[i][componentForm[addressType]];
                    document.getElementById(addressType).value = val;
                }
            }
        }
        // [END region_fillform]

        // [START region_geolocation]
        // Bias the autocomplete object to the user's geographical location,
        // as supplied by the browser's 'navigator.geolocation' object.
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
        // [END region_geolocation]
    </script>


    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body onload="initialize()">
<div class="container-fluid">
<div class="row">
    <nav class="navbar navbar-default navbar-fixed-top nav-transparent-shrink" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
                <span class="sr-only">Toggle navigation</span>
            </button>
            <a class="navbar-brand" href="#"></a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse-01">
            <ul class="nav navbar-nav navbar-left">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Categories
                        <b class="caret"></b>
                    </a>
                    <span class="dropdown-arrow"></span>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#">Business</a>
                        </li>
                        <li>
                            <a href="#">Music</a>
                        </li>
                        <li>
                            <a href="#">Food &amp; Drink</a>
                        </li>
                        <li>
                            <a href="#">Film &amp; Media</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">All</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#">Login</a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="signUp.html">Sign up</a>
                </li>
            </ul>
        </div>
    </nav>
</div>

<div class="row" style="background: #000">
    <div id="background-block" class="flow-img" style="background-image: url(../media/images/bg_mask.png), url(media/images/events/default/concert-smoke.jpg)"></div>
</div>

<div class="row">
    <div class="on-background-block-header col-md-10 col-md-offset-1">
        <h4>Create event for free</h4>
    </div>
    <div class="on-background-block col-md-10 col-md-offset-1">
        <div class="hidden-lg hidden-md">
            <h4>Create event for free</h4>
        </div>
        <form action="SomeServlet" method="post" id="create-event-form">
            <div class="form-group">
                <label for="event-name">Event Name</label>
                <input type="text" class="form-control" placeholder="Choose Event Name" name="event_name" id="event-name" required />
            </div>
            <div class="form-group">
                <label for="datepicker-start">Start Date &amp; Time</label>
                <div class="input-prepend input-datepicker">
                    <button type="button" class="btn">
                        <span class="fui-calendar"></span>
                    </button>
                    <input type="text" placeholder="Start date" name="start_date" id="datepicker-start" readonly="" required >
                </div>
                <input type="text" class="form-control time" placeholder="00:00" name="start_time" required >
            </div>
            <div class="form-group">
                <label for="datepicker-end">End Date &amp; Time</label>
                <div class="input-prepend input-datepicker">
                    <button type="button" class="btn">
                        <span class="fui-calendar"></span>
                    </button>
                    <input type="text" placeholder="End date" name="end_date" id="datepicker-end" readonly="" required >
                </div>
                <input type="text" class="form-control time" placeholder="00:00" name="end_time" required >
            </div>
            <div class="form-group">
                <label for="event-category">Category</label>
                <select name="event_category" class="select-block" id="event-category" form="create-event-form">
                    <option value="select_category">
                        Select categoty...
                    </option>
                    <option value="business">
                        Business
                    </option>
                    <option value="music">
                        Music
                    </option>
                    <option value="food_and_drink">
                        Food &amp; Drink
                    </option>
                    <option value="film_and_media">
                        Film &amp; Media
                    </option>
                    <option value="other">
                        Other
                    </option>
                </select>
            </div>
            <div class="form-group">
                <label for="event-type">Type</label>
                <select name="event_type" class="select-block" id="event-type" form="create-event-form">
                    <option value="select_type">
                        Select type...
                    </option>
                    <option value="conference">
                        Conference
                    </option>
                    <option value="party">
                        Party
                    </option>
                    <option value="concert">
                        Concert
                    </option>
                    <option value="other">
                        Other
                    </option>
                </select>
            </div>
            <div class="form-group">
                <label for="event-background">Event Background</label>
                <div class="input-group">
							<span class="input-group-btn">
								<span class="btn btn-primary btn-file">
									Open
									<input type="file" name="event_background" id="event-background" accept="image/*" />
								</span>
							</span>
                    <input type="text" class="form-control" readonly="">
                </div>
            </div>
            <div class="form-group">
                <label for="event-details">Event Details</label>
                <textarea rows="4" placeholder="Tell users about your event" class="form-control" id="event-details" form="create-event-form"></textarea>
            </div>
            <div class="form-group">
                <label for="event-location">Address</label>
                <input type="text" class="form-control" placeholder="Enter Address for your event" name="event_location" id="event-location" />
            </div>
            <div class="form-group">
                <button class="btn btn-primary btn-lg btn-block" name="submit" type="submit" value="Submit">
                    Submit
                </button>
            </div>
        </form>
    </div>
</div>

<div class="row">
    <div class="bottom-menu">
        <div class="col-md-2 col-sm-2 col-xs-12 col-md-offset-1 col-sm-offset-1 brand">
            <a class="navbar-brand" href="#"></a>
        </div>

        <div class="col-md-7 col-sm-6">
            <ul class="bottom-links">
                <li>
                    <a href="#fakelink">About Us</a>
                </li>
                <li>
                    <a href="#fakelink">Store</a>
                </li>
                <li>
                    <a href="#fakelink">Privacy</a>
                </li>
                <li>
                    <a href="#fakelink">Follow Us</a>
                </li>
                <li>
                    <a href="#fakelink">Support</a>
                </li>
            </ul>
        </div>

        <div class="col-md-2 col-sm-3">
            <ul class="bottom-icons">
                <li>
                    <a href="#fakelink" class="fui-googleplus"></a>
                </li>
                <li>
                    <a href="#fakelink" class="fui-facebook"></a>
                </li>
                <li>
                    <a href="#fakelink" class="fui-twitter"></a>
                </li>
            </ul>
        </div>
    </div>
    <!-- /bottom-menu-inverse -->
</div>
</div>

<!-- Load JS here for greater good =============================-->
<script src="js/classie.js"></script>
<script src="js/cbpAnimatedHeader.min.js"></script>

<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/jquery.ui.touch-punch.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-select.js"></script>
<script src="js/bootstrap-switch.js"></script>
<script src="js/flatui-checkbox.js"></script>
<script src="js/flatui-radio.js"></script>
<script src="js/jquery.tagsinput.js"></script>
<script src="js/jquery.placeholder.js"></script>

<script src="js/application.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("#background-block").css("height", 4 * $(window).height() / 5);
    });
    $(window).resize(function() {
        $("#background-block").css("height", 4 * $(window).height() / 5);
    });
</script>

</body>

</html>
