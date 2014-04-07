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
             style="background-image: url(media/images/bg_mask.png), url(media/images/default/user-page.jpg)"></div>
    </div>

    <div class="row">
        <div class="on-background-block col-md-7 col-md-offset-1">
            <div class="row">
                <div class="profile-left">
                    <img class="profile-img" src="media/images/users/${user.image}">
                    <ul>
                        <li>
                            <a href="mailto:${user.email}">
                                <span class="fui-mail"></span>
                                <fmt:message key="organizerPage.sendEmail"/>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="profile-details">
                    <h2>${user.firstName} ${user.lastName}</h2>
                    <dl class="dl-horizontal">
                        <c:if test="${user.gender != ''}">
                            <dt>Gender</dt>
                            <dd>${user.gender}</dd>
                        </c:if>
                        <c:if test="${user.country != ''}">
                            <dt>Country</dt>
                            <dd>${user.country}</dd>
                        </c:if>
                        <c:if test="${user.city != ''}">
                            <dt>City</dt>
                            <dd>${user.city}</dd>
                        </c:if>
                        <c:if test="${user.about != ''}">
                            <dt>About</dt>
                            <dd>${user.about}</dd>
                        </c:if>
                        <c:if test="${user.site != ''}">
                            <dt>Site</dt>
                            <dd>
                                <a href="${user.site}">${user.site}</a>
                            </dd>
                        </c:if>
                    </dl>
                </div>
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
<script src="js/bootstrap.min.js"></script>

<script src="js/application.js"></script>

</body>

</html>
