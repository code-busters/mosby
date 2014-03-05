<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Mosby - event managment</title>
    <link rel="shortcut icon" href="media/images/favicon.ico">
    <link rel="icon" type="image/png" href="media/images/favicon.png" />
    <meta name="description" content="Mosby - make it simple. New event managment system" />

    <meta name="viewport" content="width=1000, initial-scale=1.0, maximum-scale=1.0">

    <!-- Loading Bootstrap -->
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="../css/flat-ui.css" rel="stylesheet">
    <link href="../css/styles.css" rel="stylesheet">


    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
    <script src="../js/html5shiv.js"></script>
    <script src="../js/respond.min.js"></script>
    <![endif]-->

    <!--	AIzaSyD548jnqtWftyB35lh_iMInJQhedC1XRc8   -->
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyD548jnqtWftyB35lh_iMInJQhedC1XRc8&sensor=false">
    </script>

    <script>
        var myCenter = new google.maps.LatLng(49.839683, 24.029717);

        function initialize() {
            var mapProp = {
                center: myCenter,
                zoom: 15,
                mapTypeControl: true,
                mapTypeControlOptions: {
                    style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
                },
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };

            var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

            var marker = new google.maps.Marker({
                position: myCenter
            });

            marker.setMap(map);

            var infowindow = new google.maps.InfoWindow({
                content: "Here goes full adress"
            });

            google.maps.event.addListener(marker, 'click', function() {
                infowindow.open(map, marker);
            });
        }

        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-default navbar-fixed-top nav-transparent" role="navigation">
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
                        <a href="#">Sing up</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>

    <div class="row" style="background: #000">
        <div id="event-background" class="flow-img" style="background-image: url(../media/images/bg_mask.png), url(../media/images/events/background/metalica1.jpg)"></div>
    </div>

    <div class="row">
        <div class="event-details col-md-7 col-md-offset-1">
            <ul class="event-detail">
                <li>
                    <span class="fui-calendar-solid"></span>22/03/2014</li>
                <li>
                    <span class="fui-time"></span>21:00-00:00</li>
                <li>
                    <span class="fui-location"></span>Lviv, Ukraine</li>
            </ul>
            <h2>Metallica mega tour</h2>
            <p>
                The Big Four Tribute Band are an act dedicated to replicating the four biggest thrash metal bands in the world - Metallica, Megadeth, Slayer and Anthrax. Never before has any one band attempted such a feat. The Big Four Tribute have a reputation for delivering each song with deadly accuracy note For note, scream for scream, beat for beat. Close your eyes and you will feel like you are listening to each band live at their peak in the 1980's! Each and every gig has been a crowd pleasing success due to the diverse vocals of lead singer and Bass guitarist – Carlo Caci, his twin brother and lead guitarist John Caci, Rhythm guitarist Phil Wright and Drummer Sam Ogden. The band formed in 2011 consisting from members of the UK metal band Betrayal. With over 8 years of gigging experience and official theme tune creators for Sky Sports Cage fighting event - ‘Full Contact Contender’, Betrayal’s members are truly experienced in entertaining the crowd. The Big four tribute bands gigs have been hugely successful in drawing out the audiences just from the big fours name and Betrayal reputation in the Midlands alone.
            </p>
            <div id="googleMap" class="col-md-12" style="height:380px;"></div>
        </div>
        <div class="organizer-details col-md-3">
            <img src="media/images/users/GDG-Lviv.png">
            <h3>GDG Lviv</h3>
            <p>
                Google Developer Group (GDG) Lviv meets for about two times a month in office of Lviv's IT companies. The typical meeting formats are techtalk, bar camp or training, also from time to time we organize hackathons. <br><br>

                Who we are? Open and volunteer geek communities who create exciting projects and share experience about Google technology with passion.
            </p>
            <span class="fui-mail"></span>
            <span class="fui-facebook"></span>
            <span class="fui-googleplus"></span>
            <span class="fui-twitter"></span>
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
<script src="../js/classie.js"></script>
<script src="../js/cbpAnimatedHeader.min.js"></script>

<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="../js/jquery.ui.touch-punch.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/bootstrap-select.js"></script>
<script src="../js/bootstrap-switch.js"></script>
<script src="../js/flatui-checkbox.js"></script>
<script src="../js/flatui-radio.js"></script>
<script src="../js/jquery.tagsinput.js"></script>
<script src="../js/jquery.placeholder.js"></script>


<script src="http://vjs.zencdn.net/4.3/video.js"></script>
<script src="../js/application.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("#event-background").css("height", 4 * $(window).height() / 5);
    });
    $(window).resize(function() {
        $("#event-background").css("height", 4 * $(window).height() / 5);
    });
</script>

</body>

</html>