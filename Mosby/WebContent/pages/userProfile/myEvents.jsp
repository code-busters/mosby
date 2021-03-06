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
    <title><fmt:message key="myEvents.myEvents"/> - Mosby - <fmt:message key="title"/></title>
    <link rel="shortcut icon" href="media/images/favicon.ico">
    <link rel="icon" type="image/png" href="media/images/favicon.png"/>
    <meta name="description" content="Mosby - make it simple. New event management system"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">
    <link href="css/pro-features.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap-social.css" rel="stylesheet">
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
             style="background-image: url(media/images/bg_mask.png), url(media/images/default/user-profile.jpg)"></div>
    </div>

    <div id="wrapper" class="row user-profile">
        <jsp:include page="parts/sideNavbar.jsp"/>
        <div id="page-content-wrapper">
            <div class="content-header">
                <h1>
                    <a id="menu-toggle" href="#" class="btn btn-default">
                        <span class="fui-list-columned"></span>
                    </a>
                    <fmt:message key="myEvents.myEvents"/>
                </h1>
            </div>
            <!-- Keep all page content within the page-content inset div! -->
            <div class="page-content inset">
                <div class="row">
                    <div id="my-events">
                        <div class="row my-events-header hidden-xs">
                            <div class="col-md-6 col-sm-6"><fmt:message key="myEvents.eventName"/></div>
                            <div class="col-md-2 col-sm-2"><fmt:message key="myEvents.startDate"/></div>
                            <div class="col-md-2 col-sm-2"><fmt:message key="myEvents.sold"/></div>
                            <div class="col-md-2 col-sm-2 text-center"><fmt:message key="myEvents.actions"/></div>
                        </div>
                        <div id="my-events-body">
                            <c:forEach items="${events}" var="event">
                                <div id="${event.id}" class="row">
                                    <div class="col-md-6 col-sm-6">
                                        <span class="as-label visible-xs"><fmt:message key="myEvents.eventName"/></span>
                                        <a href="<c:url value="/eventPage?eventId=${event.id}"/>">${event.name}</a>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <span class="as-label visible-xs"><fmt:message key="myEvents.startDate"/></span>
                                        <p>${event.startDate}</p>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <span class="as-label visible-xs"><fmt:message key="myEvents.sold"/></span>
                                        <c:set var="eventId" value="${event.id}"/>
                                        <p>${ticketsSold[eventId]}/${tickets[eventId]}</p>
                                    </div>
                                    <div class="col-md-2 col-sm-2 actions text-center">
                                        <label class="visible-xs text-left"><fmt:message key="myEvents.actions"/></label>
                                        <a href="<c:url value="/editEvent?eventId=${event.id}"/>">
                                            <span class="fui-new"></span>
                                        </a>
                                        <a href="<c:url value="/myEvents?delete=${event.id}"/>">
                                            <span class="fui-trash"></span>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-3 add-button">
                        <a id="free-ticket" href="<c:url value="/createEvent"/>"
                           class="btn btn-block btn-lg btn-primary">
                            <span class="fui-plus"></span><fmt:message key="myEvents.event"/></a>
                    </div>
                </div>
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
<script src="js/typeahead.bundle.min.js"></script>

<script src="js/application.js"></script>

</body>

</html>