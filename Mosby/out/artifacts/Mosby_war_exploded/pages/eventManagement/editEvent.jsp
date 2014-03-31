<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="main.java.com.mosby.i18n.text"/>
<html>

<head>
    <meta charset="utf-8">
    <title><fmt:message key="editEvent.editEvent"/> - Mosby - <fmt:message key="title"/></title>
    <link rel="shortcut icon" href="media/images/favicon.ico">
    <link rel="icon" type="image/png" href="media/images/favicon.png"/>
    <meta name="description"
          content="Mosby - make it simple. New event management system"/>

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">
    <link href="css/pro-features.css" rel="stylesheet">
    <link href="css/simple-sidebar.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">


    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="container-fluid">
<div class="row">
    <jsp:include page="../parts/navbar.jsp"/>
</div>

<div class="row" style="background: #000">
    <div id="background-block" class="flow-img"
         style="background-image: url(media/images/bg_mask.png), url(media/images/events/background/${event.background})"></div>
</div>

<div id="wrapper" class="row user-profile">
    <jsp:include page="parts/sideNavnar.jsp"/>
    <div id="page-content-wrapper">
        <div class="content-header">
            <h3>
                <a id="menu-toggle" href="#" class="btn btn-default"> <span
                        class="fui-list-columned"></span>
                </a> <fmt:message key="editEvent.editEventDetails"/>
            </h3>
        </div>
        <div class="page-content inset">
            <div class="errors">
                <c:forEach items="${errors}" var="error">
                    <p>${error}</p>
                </c:forEach>
            </div>
            <form action="editEvent" enctype="multipart/form-data"
                  method="post" id="edit-event-form">
                <input type="hidden" name="eventId" value="${event.id}"/>
                <h5><fmt:message key="editEvent.eventDetail"/></h5>

                <div class="form-group">
                    <label for="event-name"><fmt:message key="editEvent.eventName"/></label> <input type="text"
                                                                      class="form-control"
                                                                      placeholder="<fmt:message key="editEvent.chooseEventName"/>"
                                                                      value="${event.name}" name="event_name"
                                                                      id="event-name" required/>
                </div>
                <div class="form-group">
                    <label for="open-logo"><fmt:message key="editEvent.eventLogo"/></label> <input
                        type="file" class="hide" name="event_logo" id="open-logo"
                        accept="image/*"/> <label for="open-logo"> <c:choose>
                    <c:when test="${empty event.logo}">
                        <span id="backup-img" class="hide">media/images/events/logo/default.png</span>
                        <img class="logo"
                             src="media/images/events/logo/default.png">
                    </c:when>
                    <c:otherwise>
                        <span id="backup-img" class="hide">media/images/events/logo/${event.logo}</span>
                        <img class="logo"
                             src="media/images/events/logo/${event.logo}">
                    </c:otherwise>
                </c:choose>
                </label> <span class="change-img-name"></span> <span
                        class="additional-input-info"><fmt:message key="editEvent.clickOnImageToBrowseYourLogo"/></span>
                </div>
                <div class="form-group">
                    <label for="event-background"><fmt:message key="editEvent.eventBackground"/></label>

                    <div class="input-group">
								<span class="input-group-btn"> <span
                                        class="btn btn-primary btn-file"> <fmt:message key="editEvent.open"/> <span
                                        id="backup-img"
                                        class="hide">media/images/events/default/concert-smoke.jpg</span>
										<input type="file" name="event_background"
                                               id="event-background" accept="image/*"/>
								</span>
								</span> <input type="text" class="form-control" readonly=""
                                               disabled="disabled">
                    </div>
                    <span class="change-img-name"></span> <span
                        class="additional-input-info"><fmt:message key="editEvent.selectImageWithBigResolutionForBetterResult"/></span>
                </div>
                <div class="form-group">
                    <label for="datepicker-start"><fmt:message key="editEvent.startDateTime"/></label>

                    <div class="input-prepend input-datepicker">
                        <button type="button" class="btn">
                            <span class="fui-calendar"></span>
                        </button>
                        <input type="text" placeholder="<fmt:message key="editEvent.startDate"/>" name="start_date"
                               value="${event.startDate}" id="datepicker-start" readonly=""
                               required>
                    </div>
                    <input type="time" class="form-control time"
                           value="${event.startTime}" name="start_time" required>
                </div>
                <div class="form-group">
                    <label for="datepicker-end"><fmt:message key="editEvent.endDateTime"/></label>

                    <div class="input-prepend input-datepicker">
                        <button type="button" class="btn">
                            <span class="fui-calendar"></span>
                        </button>
                        <input type="text" placeholder="<fmt:message key="editEvent.endDate"/>" name="end_date"
                               value="${event.endDate}" id="datepicker-end" readonly=""
                               required>
                    </div>
                    <input type="time" class="form-control time"
                           value="${event.endTime}" name="end_time" required>
                </div>


                <div class="form-group">
                    <label for="event-category"><fmt:message key="editEvent.category"/></label> <select
                        name="event_category" class="select-block" id="event-category"
                        form="edit-event-form">
                    <c:set var="eventCategory"
                           value="${event.eventCategory.category}"/>
                    <c:forEach items="${eventCategories}" var="category">
                        <c:set var="tempCategory" value="${category.category}"/>
                        <option value="${category.id}"
                                <c:if test="${eventCategory == tempCategory}"> selected </c:if>>
                                ${category.category}</option>
                    </c:forEach>
                </select>
                </div>
                <div class="form-group">
                    <label for="event-type"><fmt:message key="editEvent.type"/></label> <select name="event_type"
                                                                 class="select-block" id="event-type"
                                                                 form="edit-event-form">
                    <c:set var="eventType" value="${event.eventType.type}"/>
                    <c:forEach items="${eventTypes}" var="type">
                        <c:set var="tempType" value="${type.type}"/>
                        <option value="${type.id}"
                                <c:if test="${eventType == tempType}"> selected </c:if>>
                                ${type.type}</option>
                    </c:forEach>
                </select>
                </div>
                <div class="form-group">
                    <label for="event-description"><fmt:message key="editEvent.eventDescription"/></label>
                    <textarea rows="4" placeholder="<fmt:message key="editEvent.tellUsersAboutYourEvent"/>"
                              class="form-control" name="event_description"
                              id="event-description"
                              form="edit-event-form">${event.description}</textarea>
                </div>
                <div class="form-group">
                    <label for="event-location"><fmt:message key="editEvent.address"/></label> <input type="text"
                                                                       class="form-control"
                                                                       placeholder="<fmt:message key="editEvent.enterAddressForYourEvent"/>"
                                                                       value="${event.location}"
                                                                       name="event_location"
                                                                       id="event-location" onFocus="geolocate()"/>
                </div>
                <div class="form-group" id="googleMap" style="height: 380px;"></div>

                <h5><fmt:message key="editEvent.additionalSettings"/></h5>

                <div class="form-group">
                    <span class="as-label"><fmt:message key="editEvent.listingPrivacy"/></span>
                    <label class="radio <c:if test="${event.privacy == false}"> checked </c:if>">
                            <span class="icons">
                                <span class="first-icon fui-radio-unchecked"></span>
                                <span class="second-icon fui-radio-checked"></span>
							</span>
                        <input type="radio" name="privacy_event" id="public-event"
                               value="0" data-toggle="radio">
                        <fmt:message key="editEvent.publicEvent"/>
                        <span class="additional"><fmt:message key="editEvent.listThisEventOnEventbriteAndSearchEngines"/></span>
                    </label>
                    <label class="radio <c:if test="${event.privacy}"> checked </c:if>">
                            <span class="icons">
                                <span class="first-icon fui-radio-unchecked"></span>
                                <span class="second-icon fui-radio-checked"></span>
							</span>
                        <input type="radio" name="privacy_event" id="private-event"
                               value="1" data-toggle="radio">
                        <fmt:message key="editEvent.privateEvent"/>
                        <span class="additional"><fmt:message key="editEvent.doNotListThisEventPublicly"/></span>
                    </label>
                </div>

                <div class="form-group">
                    <label for="organizer"><fmt:message key="editEvent.organizeBy"/></label>
                    <select name="organizer" class="select-block" id="organizer" form="edit-event-form">
                        <c:forEach items="${organizers}" var="organizer">
                            <option value="${organizer.id}" <c:if
                                    test="${organizer.id == event.organizer.id}"> selected </c:if>>
                                    ${organizer.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-4 col-md-offset-4">
                    <button class="btn btn-primary btn-lg btn-block" name="submit"
                            type="submit" value="Submit"><fmt:message key="editEvent.save"/>
                    </button>
                </div>
            </form>
        </div>
    </div>

</div>

<div class="row">
    <jsp:include page="../parts/footer.jsp"/>
</div>
</div>

<!-- Load JS here for greater good =============================-->
<script src="js/classie.js"></script>
<script src="js/cbpAnimatedHeader.min.js"></script>
<script src="js/uisearch.js"></script>

<script src="js/jquery-2.0.3.min.js"></script>
<script src="js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/jquery.ui.touch-punch.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-select.js"></script>
<script src="js/bootstrap-switch.js"></script>
<script src="js/flatui-checkbox.js"></script>
<script src="js/flatui-radio.js"></script>
<script src="js/jquery.tagsinput.js"></script>
<script src="js/jquery.placeholder.js"></script>
<script src="js/jquery.stacktable.js"></script>
<script src="http://vjs.zencdn.net/4.3/video.js"></script>

<!--	AIzaSyD548jnqtWftyB35lh_iMInJQhedC1XRc8   -->
<!--		<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyD548jnqtWftyB35lh_iMInJQhedC1XRc8&sensor=true&libraries=places"></script>-->
<script
        src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true&libraries=places"></script>
<script>
    var map, autocomplete, geocoder;

    function initialize() {
        var mapProp = {
            zoom: 15,
            mapTypeControl: true,
            mapTypeControlOptions: {
                mapTypeIds: [ google.maps.MapTypeId.ROADMAP,
                    google.maps.MapTypeId.SATELLITE ],
                style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
            }
        };

        map = new google.maps.Map(document.getElementById("googleMap"),
                mapProp);

        var input = (document.getElementById('event-location'));
        geocoder = new google.maps.Geocoder();

        geocoder
                .geocode(
                {
                    'address': input.value
                },
                function (results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        map.setCenter(results[0].geometry.location);
                        var marker = new google.maps.Marker({
                            map: map,
                            position: results[0].geometry.location
                        });
                    } else {
                        alert('Geocode was not successful for the following reason: '
                                + status);
                    }
                });

        autocomplete = new google.maps.places.Autocomplete(input);
        autocomplete.bindTo('bounds', map);

        var infowindow = new google.maps.InfoWindow();
        var marker = new google.maps.Marker({
            map: map
        });

        google.maps.event
                .addListener(
                autocomplete,
                'place_changed',
                function () {
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
                            (place.address_components[0]
                                    && place.address_components[0].short_name || ''),
                            (place.address_components[1]
                                    && place.address_components[1].short_name || ''),
                            (place.address_components[2]
                                    && place.address_components[2].short_name || '') ]
                                .join(' ');
                    }

                    infowindow.setContent('<div><strong>'
                            + place.name + '</strong><br>'
                            + address);
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
            navigator.geolocation
                    .getCurrentPosition(function (position) {
                        var geolocation = new google.maps.LatLng(
                                position.coords.latitude,
                                position.coords.longitude);
                        autocomplete
                                .setBounds(new google.maps.LatLngBounds(
                                        geolocation, geolocation));
                    });
        }
    }

    google.maps.event.addDomListener(window, 'load', initialize);
</script>

<script src="js/application.js"></script>

<script type="text/javascript">
    $(document).on("keypress", 'form', function (e) {
        var code = e.keyCode || e.which;
        if (code == 13) {
            e.preventDefault();
            return false;
        }
    });
</script>

</body>

</html>