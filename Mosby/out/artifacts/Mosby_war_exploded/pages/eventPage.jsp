<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="main.java.com.mosby.i18n.text"/>
<html>

<head>
    <meta charset="utf-8">
    <title><fmt:message key="eventPage.event"/> - Mosby - <fmt:message key="title"/></title>
    <link rel="shortcut icon" href="media/images/favicon.ico">
    <link rel="icon" type="image/png" href="media/images/favicon.png"/>
    <meta name="description" content="Mosby - make it simple. New event management system"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">
    <link href="css/pro-features.css" rel="stylesheet">
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
        <jsp:include page="parts/navbar.jsp"/>
    </div>

    <div class="row" style="background: #000">
        <div id="background-block" class="flow-img big-background"
             style="background-image: url(media/images/bg_mask.png), url(media/images/events/background/${event.background})"></div>
    </div>

    <div class="row">
        <div class="on-background-block event col-md-7 col-md-offset-1">
            <div class="breadcrumb-text">
                <p>
                    <a href="#fakelink" class="text-primary">${event.eventCategory.category}</a>
                    <a href="#fakelink" class="text-info">${event.eventType.type}</a>
                </p>
            </div>
            <ul class="event-detail">
                <li>
                    <span class="fui-calendar-solid"></span>
                    ${event.startDate} - ${event.endDate}
                </li>
                <li>
                    <span class="fui-time"></span>
                    ${event.startTime} - ${event.endTime}
                </li>
                <li>
                    <span class="fui-location"></span>
                    ${event.location}
                </li>
            </ul>
            <h2>${event.name}</h2>

            <c:if test="${event.logo != 'default.png'}">
                <div class="center-block">
                    <img class="event-logo img-responsive" src="media/images/events/logo/${event.logo}">
                </div>
            </c:if>

            <p>
                ${event.description}
            </p>

            <div id="googleMap" class="" style="height:380px;"></div>
            <c:if test="${(fn:length(tickets) > 0) && (ticketInfo.quantityAvailable > 0)}">
                <form action="register" method="post" id="register-for-event-form">
                    <input type="hidden" name="eventId" value="${event.id}"/>

                    <div id="tickets">
                        <div class="row create-tickets-header hidden-xs">
                            <div class="col-md-8 col-sm-8"><fmt:message key="eventPage.ticketType"/></div>
                            <div class="col-md-2 col-sm-2"><fmt:message key="eventPage.price"/></div>
                            <div class="col-md-2 col-sm-2"><fmt:message key="eventPage.quantity"/></div>
                        </div>
                        <div id="tickets-body">
                            <c:forEach items="${tickets}" var="ticketInfo">
                                <div id="0" class="row">
                                    <div class="col-md-8 col-sm-8">
                                        <h6 class="visible-xs"><fmt:message key="eventPage.ticketType"/></h6>

                                        <p>${ticketInfo.name}</p>

                                        <p class="ticket-info">
                                                ${ticketInfo.description}
                                        </p>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <h6 class="visible-xs"><fmt:message key="eventPage.price"/></h6>
                                        <c:choose>
                                            <c:when test="${ticketInfo.type == 'Free'}">
                                                <p><fmt:message key="eventPage.free"/></p>
                                            </c:when>
                                            <c:when test="${ticketInfo.type == 'Donation'}">
                                                <input type="number" class="form-control" value="0" placeholder="0"
                                                       name="ticket_price_${ticketInfo.id}">
                                                <span class="additional-input-info">*Donation</span>
                                            </c:when>
                                            <c:otherwise>
                                                <p> ${ticketInfo.price} </p>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <h6 class="visible-xs"><fmt:message key="eventPage.quantity"/></h6>
                                        <input type="number" class="form-control" placeholder="0"
                                               name="ticket_quantity_${ticketInfo.id}" id="promo-code-discount-0"
                                               min="0"
                                               max="${ticketInfo.quantityAvailable}">
                                        <span class="additional-input-info"><fmt:message
                                                key="eventPage.max"/> ${ticketInfo.quantityAvailable}</span>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="row ">
                            <div class="col-md-5">
                                <label class="checkbox" for="checkbox-promo-code">
                                    <input type="checkbox" value="" id="checkbox-promo-code" data-toggle="checkbox">
                                    <fmt:message key="eventPage.doYouHavePromoCode"/>?
                                </label>
                            </div>
                            <div id="promo-code-div" class="pull-right">
                                <div class="col-md-5 text-right">
                                    <label for="promo-code"><fmt:message key="eventPage.promoCode"/>:</label>
                                </div>
                                <div class="col-md-7">
                                    <input type="text" class="form-control"
                                           placeholder="<fmt:message key="eventPage.enterCode"/>" name="promo_code"
                                           id="promo-code"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--END TICKETS-->
                    <div class="col-md-3 col-sm-3 col-md-offset-9 col-sm-offset-9">
                        <button class="btn btn-primary btn-lg btn-block" name="submit" type="submit" value="Order Now">
                            <fmt:message key="eventPage.orderNow"/>
                        </button>
                    </div>
                </form>
            </c:if>
        </div>
        <div class="col-md-3">
            <div class="organizer-details col-md-12">
                <c:if test="${event.organizer.logo != 'default.png'}">
                    <img class="img-responsive" src="media/images/organizers/${event.organizer.logo}">
                </c:if>

                <h3>${event.organizer.name}</h3>
                <c:if test="${event.organizer.about != null}">
                    <p>${event.organizer.about}</p>
                </c:if>
                <a href="<c:url value='/organizerPage'/>?id=${event.organizer.id}" class="btn btn-block btn-lg btn-default button-to-page">
                    <span class="fui-user"></span>Organizer page
                </a>
            </div>
            <div class="event-actions col-md-12">
                <a href="${calendarURL}" class="btn btn-block btn-lg btn-primary">
                    <span class="fui-plus"></span>
                    <fmt:message key="eventPage.addToGoogleCalendar"/>
                </a>
            </div>
        </div>
    </div>

    <div class="row">
        <jsp:include page="parts/footer.jsp"/>
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

<!--	AIzaSyD548jnqtWftyB35lh_iMInJQhedC1XRc8   -->
<!--	<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyD548jnqtWftyB35lh_iMInJQhedC1XRc8&sensor=false"></script>-->
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>

<script>
    var map, geocoder;
    var address = '${event.location}';

    function initialize() {
        geocoder = new google.maps.Geocoder();
        var mapProp = {
            zoom: 15,
            mapTypeControl: true,
            mapTypeControlOptions: {
                mapTypeIds: [google.maps.MapTypeId.ROADMAP, google.maps.MapTypeId.SATELLITE],
                style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
            }
        };


        map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
        geocoder.geocode({
            'address': address
        }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                map.setCenter(results[0].geometry.location);
                var marker = new google.maps.Marker({
                    map: map,
                    position: results[0].geometry.location
                });
            } else {
                alert('Geocode was not successful for the following reason: ' + status);
            }
        });
    }
    google.maps.event.addDomListener(window, 'load', initialize);
</script>

<script src="js/application.js"></script>

</body>

</html>