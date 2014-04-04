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
    <title><fmt:message key="organizerPage.organizer"/> - Mosby - <fmt:message key="title"/></title>
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
             style="background-image: url(media/images/bg_mask.png), url(media/images/default/organizer-page.jpg)"></div>
    </div>

    <div class="row">
        <div class="on-background-block col-md-7 col-md-offset-1">
            <div class="row">
                <div class="profile-left">
                    <c:if test="${organizer.logo != 'default.png'}">
                        <img class="profile-img" src="media/images/organizers/${organizer.logo}">
                    </c:if>
                    <ul>
                        <c:if test="${organizer.phone != ''}">
                            <li>
                                <span class="fui-user"></span>
                                    ${organizer.phone}
                            </li>
                        </c:if>
                        <c:if test="${organizer.email != ''}">
                            <li>
                                <a href="mailto:${organizer.email}">
                                    <span class="fui-mail"></span>
                                    <fmt:message key="organizerPage.sendEmail"/>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${organizer.facebook != ''}">
                            <li>
                                <a href="${organizer.facebook}">
                                    <span class="fui-facebook"></span>
                                    Facebook
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${organizer.googlePlus != ''}">
                            <li>
                                <a href="${organizer.googlePlus}">
                                    <span class="fui-googleplus"></span>
                                    Google+
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${organizer.twitter != ''}">
                            <li>
                                <a href="${organizer.twitter}">
                                    <span class="fui-twitter"></span>
                                    Twitter
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </div>
                <div class="profile-details">
                    <h2>${organizer.name}</h2>
                    <dl class="dl-horizontal">
                        <c:if test="${organizer.about != ''}">
                            <dt>About</dt>
                            <dd>${organizer.about}</dd>
                        </c:if>
                        <c:if test="${organizer.site != ''}">
                            <dt>Site</dt>
                            <dd>
                                <a href="${organizer.site}">${organizer.site}</a>
                            </dd>
                        </c:if>
                    </dl>
                </div>
            </div>
            <div class="row organizer-events">
                <h4><fmt:message key="organizerPage.myEvents"/></h4>
                <c:forEach items="${events}" var="event">
                    <div class="event">
                        <div class="event-image flow-img"
                             style="background-image:url(media/images/events/background/${event.background})"></div>
                        <div class="event-detail">
                            <h2>${event.name}</h2>
                            <ul>
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
                            <p class="ellipsis-3">
                                    ${event.description}
                            </p>

                            <div class="text-right read-more">
                                <a href="<c:url value="/eventPage?eventId=${event.id}"/>"><fmt:message
                                        key="organizerPage.readMore"/> ></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
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
<script src="js/jquery.ellipsis.js"></script>


<script src="http://vjs.zencdn.net/4.3/video.js"></script>
<script src="js/application.js"></script>

<script type="text/javascript">
    $('.ellipsis-3').ellipsis({
        row: 3
    });
</script>

</body>

</html>
