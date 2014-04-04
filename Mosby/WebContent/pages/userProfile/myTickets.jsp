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
                    <fmt:message key="myTickets.myTickets"/>
                </h1>
            </div>
            <div class="page-content inset">
                <div class="row">
                    <div id="my-apis">
                        <div class="row my-apis-header hidden-xs">
                            <div class="col-md-6 col-sm-6"><fmt:message key="myTickets.eventName"/></div>
                            <div class="col-md-2 col-sm-2"><fmt:message key="myTickets.organizer"/></div>
                            <div class="col-md-1 col-sm-1"><fmt:message key="myTickets.price"/></div>
                            <div class="col-md-2 col-sm-2"><fmt:message key="myTickets.startDate"/></div>
                            <div class="col-md-1 col-sm-1 text-center"><fmt:message key="myTickets.actions"/></div>
                        </div>
                        <div id="my-apis-body">
                            <c:forEach items="${tickets}" var="ticket">
                                <div id="${ticket.id}" class="row">
                                    <div class="col-md-6 col-sm-6">
                                        <span class="as-label visible-xs"><fmt:message key="myTickets.eventName"/></span>
                                        <a href="<c:url value="/eventPage?eventId=${ticket.event.id}"/>"
                                           target="_blank">${ticket.event.name}</a>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <span class="as-label visible-xs"><fmt:message key="myTickets.organizer"/></span>
                                        <a href="<c:url value='/organizerPage'/>?id=${ticket.event.organizer.id}" target="_blank">${ticket.event.organizer.name}</a>
                                    </div>
                                    <div class="col-md-1 col-sm-1">
                                        <span class="as-label visible-xs"><fmt:message key="myTickets.price"/></span>
                                        <p>
                                            <c:choose>
                                                <c:when test="${ticket.ticketInfo.type == 'Free'}">
                                                    <fmt:message key="myTickets.free"/>
                                                </c:when>
                                                <c:when test="${ticket.ticketInfo.type == 'Donation'}">
                                                    <fmt:message key="myTickets.donation"/>
                                                </c:when>
                                                <c:otherwise>
                                                    ${ticket.ticketInfo.price}
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <span class="as-label visible-xs"><fmt:message key="myTickets.startDate"/></span>
                                        <p>${ticket.event.startDate}</p>
                                    </div>
                                    <div class="col-md-1 col-sm-1 actions text-center">
                                        <label class="visible-xs text-left"><fmt:message key="myTickets.actions"/></label>
                                        <a class="delete-nearby-row" href="<c:url value="/showTicket?id=${ticket.id}"/>" target="_blank">
                                            <span class="fui-export"></span>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
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

<script src="js/jquery-2.0.3.min.js"></script>

<script src="js/application.js"></script>

</body>

</html>
