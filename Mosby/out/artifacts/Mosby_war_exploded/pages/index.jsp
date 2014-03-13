<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Mosby - event managment</title>
    <link rel="shortcut icon" href="media/images/favicon.ico">
    <link rel="icon" type="image/png" href="media/images/favicon.png"/>
    <meta name="description" content="Mosby - make it simple. New event managment system"/>

    <meta name="viewport" content="width=1000, initial-scale=1.0, maximum-scale=1.0">

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

    <div id="main-idea" class="row">
        <div class="col-md-10 col-md-offset-1">
            <h1>
                Mosby - event managment system
					<span>
						Make it simple.
					</span>
            </h1>
        </div>
        <div class="scrolldown showscrolldown">
            <p>discover more</p>
        </div>
    </div>

    <div id="events" class="row">
        <div class="col-md-10 col-md-offset-1">
            <h3>Most popular</h3>
        </div>
        <c:forEach items="${eventList}" var="event">
            <div class="col-md-10 col-sm-6 col-md-offset-1 event">
                <div class="col-md-3">
                    <div class="event-image flow-img"
                         style="background-image:url(media/images/events/background/${event.background})"></div>
                </div>
                <h2>${event.name}</h2>
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
                        <span class="fui-location"></span>${event.location}
                    </li>
                </ul>
                <p>
                   ${event.description}
                </p>
                <div class="text-right">
                    <a href="<c:url value="/eventPage?eventId=${event.id}"/>">Read more >></a>
                </div>
            </div>
        </c:forEach>
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


<script src="js/jquery.backgroundvideo.min.js"></script>
<script>
    $(document).ready(function () {
        var videobackground = new $.backgroundVideo($('body'), {
            "align": "centerXY",
            "width": 1280,
            "height": 720,
            "path": "media/videos/",
            "filename": "startuppreview",
            "types": ["mp4", "ogg", "webm"]
        });
    });
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $("#events").css("margin-top", $("#main-idea").height() + $("h1").height() + 1);
        $("#main-idea").css("margin-top", 2*$(window).height()/5 - $("h1").height());
    });
    $(window).resize(function () {
        $("#events").css("margin-top", $("#main-idea").height() + $("h1").height() + 1);
        $("#main-idea").css("margin-top", 2*$(window).height()/5 - $("h1").height());
    });
</script>

</body>

</html>
