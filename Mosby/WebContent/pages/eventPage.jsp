<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Mosby - event management</title>
    <link rel="shortcut icon" href="media/images/favicon.ico">
    <link rel="icon" type="image/png" href="media/images/favicon.png"/>
    <meta name="description" content="Mosby - make it simple. New event management system"/>

    <meta name="viewport" content="width=1000, initial-scale=1.0, maximum-scale=1.0">

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
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="parts/navbar.jsp"/>
    </div>

    <div class="row" style="background: #000">
        <div id="background-block" class="flow-img"
             style="background-image: url(media/images/bg_mask.png), url(media/images/events/background/${event.background})"></div>
    </div>

    <div class="row">
        <div class="on-background-block event col-md-7 col-md-offset-1">
            <ul class="event-detail">
                <li>
                    <span class="fui-calendar-solid"></span>
                    ${event.startDate} - ${event.endDate}
                </li>
                <%--<li>--%>
                    <%--<span class="fui-time"></span>21:00-00:00--%>
                <%--</li>--%>
                <li>
                    <span class="fui-location"></span>
                    ${event.location}
                </li>
            </ul>
            <h2>${event.name}</h2>

            <c:if test="${!(empty event.logo)}">
                <div>
                    <img class="event-logo center-block" src="media/images/events/logo/${event.logo}">
                </div>
            </c:if>

            <p>
                ${event.description}
            </p>

            <div id="googleMap" class="" style="height:380px;"></div>
            <form action="registerServlet" method="post" id="register-for-event-form">
                <div id="tickets">
                    <div class="row create-tickets-header hidden-xs">
                        <div class="col-md-8 col-sm-8">Ticket type</div>
                        <div class="col-md-2 col-sm-2">Price</div>
                        <div class="col-md-2 col-sm-2">Quantity</div>
                    </div>
                    <div id="tickets-body">
                        <div id="0" class="row">
                            <div class="col-md-8 col-sm-8">
                                <h6 class="visible-xs">Ticket type</h6>
                                <p>Early bird</p>
									<span class="ticket-info">
										Include free beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer beer
									</span>
                            </div>
                            <div class="col-md-2 col-sm-2">
                                <h6 class="visible-xs">Price</h6>
                                <p>$220</p>
                            </div>
                            <div class="col-md-2 col-sm-2">
                                <h6 class="visible-xs">Quantity</h6>
                                <select name="ticket_quantity_0" value="0" class="select-block ticket-quantity" form="register-for-event-form">
                                    <option value="0" selected="selected">0</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="pull-right">
                            <div class="col-md-5 text-right">
                                <label for="promo-code">Promo code:</label>
                            </div>
                            <div class="col-md-7">
                                <input type="text" class="form-control" placeholder="Enter code" name="promo_code" id="promo-code" />
                            </div>
                        </div>
                    </div>
                </div>
                <!--END TICKETS-->
                <div class="col-md-3 col-sm-3 col-md-offset-9 col-sm-offset-9">
                    <button class="btn btn-primary btn-lg btn-block" name="submit" type="submit" value="Order Now">
                        Order Now
                    </button>
                </div>
            </form>
        </div>
        <div class="organizer-details col-md-3">
            <img src="media/images/users/GDG-Lviv.png">

            <h3>GDG Lviv</h3>

            <p>
                Google Developer Group (GDG) Lviv meets for about two times a month in office of Lviv's IT companies.
                The typical meeting formats are techtalk, bar camp or training, also from time to time we organize
                hackathons.
                <br>
                <br>Who we are? Open and volunteer geek communities who create exciting projects and share experience
                about Google technology with passion.
            </p>
            <span class="fui-mail"></span>
            <span class="fui-facebook"></span>
            <span class="fui-googleplus"></span>
            <span class="fui-twitter"></span>
        </div>
    </div>

    <div class="row">
        <jsp:include page="parts/footer.jsp"/>
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


<script src="http://vjs.zencdn.net/4.3/video.js"></script>
<script src="js/application.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $("#background-block").css("height", 4*$(window).height()/5);
    });
    $(window).resize(function () {
        $("#background-block").css("height", 4*$(window).height()/5);
    });
</script>

</body>

</html>