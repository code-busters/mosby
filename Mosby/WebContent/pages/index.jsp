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
    <title>Mosby - <fmt:message key="title"/></title>
    <link rel="shortcut icon" href="media/images/favicon.ico">
    <link rel="icon" type="image/png" href="media/images/favicon.png"/>
    <meta name="description" content="Mosby - make it simple. New event managment system"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

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
        <jsp:include page="parts/navbarUnfixed.jsp"/>
    </div>

    <%--<div class="row" style="background: #000">--%>
    <%--<div id="background-block" class="flow-img full-background" style="background-image: url(media/images/bg_mask.png), url(media/images/default/video-poster.jpg)"></div>--%>
    <%--</div>--%>

    <div class="row">
        <video id='video-player' preload='metadata' autoplay loop>
            <source src="media/videos/startuppreview.mp4"/>
            <source src="media/videos/startuppreview.webm" type='video/webm; codecs="vp8, vorbis"'/>
            <source src="media/videos/startuppreview.ogg" type='video/ogg; codecs="theora, vorbis"'/>
        </video>
    </div>

    <div id="main-idea" class="row">
        <div class="col-md-10 col-md-offset-1 col-sm-offset-1">
            <h1>
                Mosby - <fmt:message key="index.eventManagmentSystem"/>
					<span>
						<fmt:message key="index.makeItSimple"/>.
					</span>
            </h1>
        </div>
        <div class="scrolldown showscrolldown">
            <p><fmt:message key="index.discoverMore"/></p>
        </div>
    </div>

    <div id="events" class="row">
        <div class="col-md-10 col-md-offset-1">
            <h3><fmt:message key="index.mostPopular"/>:</h3>
        </div>
        <div class="col-md-10 col-md-offset-1">
            <c:forEach items="${eventList}" var="event">
                <div class="event col-md-6 col-sm-6">
                    <div class="event-image flow-img" style="background-image:url(media/images/events/background/${event.background})"></div>
                    <div class="event-detail">
                        <h2>${event.name}</h2>
                        <ul>
                            <li>
                                <span class="fui-calendar-solid"></span>
                                    ${event.startDate} - ${event.endDate}
                            </li>
                            <li>
                                <span class="fui-location"></span>${event.location}
                            </li>
                        </ul>
                        <p class="ellipsis-3">
                                ${event.description}
                        </p>
                        <div class="text-right read-more">
                            <a href="<c:url value="/eventPage?eventId=${event.id}"/>"><fmt:message key="index.readMore"/> ></a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="row">
        <jsp:include page="parts/footer.jsp"/>
    </div>
</div>

<!-- Load JS here for greater good =============================-->
<script src="js/classie.js"></script>
<script src="js/uisearch.js"></script>

<script src="js/jquery-2.0.3.min.js"></script>
<script src="js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/jquery.ui.touch-punch.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.ellipsis.js"></script>

<script src="js/application.js"></script>
<script type="text/javascript">
//    (function ($) {
//        $(document).ready(function () {
////            $("#events").css("margin-top", $("#main-idea").height() + $("h1").height() + 1);
//            $("#events").css("margin-top", 100 - $("nav").height() + "vh");
//            console.log("height " + $(window).height());
////            $("#main-idea").css("margin-top", $(window).height()/6 - $("h1").height());
//        });
//        $(window).resize(function () {
////            $("#events").css("margin-top", $("#main-idea").height() + $("h1").height() + 1);
//            $("#events").css("margin-top", $(window).height());
//            console.log("height resize " + $(window).height());
////            $("#main-idea").css("margin-top", $(window).height()/6 - $("h1").height());
//        });
//    })(jQuery);
    $('.ellipsis-3').ellipsis({
        row: 3
    });
</script>

</body>

</html>
