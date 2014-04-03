<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="main.java.com.mosby.i18n.text"/>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title><fmt:message key="aboutUs.aboutUs"/> - Mosby - <fmt:message key="title"/></title>
    <link rel="icon" type="image/png" href="media/images/favicon.png"/>
    <meta name="description" content="Mosby - make it simple. New event management system"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">
    <link href="css/pro-features.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
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
             style="background-image: url(media/images/bg_mask.png), url(media/images/default/team.jpg)"></div>
    </div>

    <div class="row">
        <div class="on-background-block-header col-md-10 col-md-offset-1">
            <h4><fmt:message key="aboutUs.aboutUs"/></h4>
        </div>
        <div class="on-background-block col-md-10 col-md-offset-1">
            <div class="text-center">
                <h2><fmt:message key="aboutUs.theFoundationOfAllGreatBrandsIsTrust"/>.</h2>

                <p class="lead">"<fmt:message key="aboutUs.theWayA"/>"</p>
            </div>
            <ul class="ch-grid">
                <li>
                    <div class="ch-item" style="background-image:url(media/images/team/oleh.jpg)">
                        <div class="ch-info">
                            <h3><fmt:message key="aboutUs.olehZasadnyy"/></h3>

                            <p>Team lead, front-end, google+</p>
								<span>
									<a href="https://plus.google.com/+OlehZasadnyy" target="_blank">
                                        <i class="fa fa-google-plus"></i>
                                    </a>
									<a href="https://www.facebook.com/oleh.zasadnyy" target="_blank">
                                        <i class="fa fa-facebook"></i>
                                    </a>
									<a href="https://github.com/ozasadnyy" target="_blank">
                                        <i class="fa fa-github"></i>
                                    </a>
								</span>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="ch-item" style="background-image:url(media/images/team/oleksii.jpg)">
                        <div class="ch-info">
                            <h3><fmt:message key="aboutUs.oleksiiKhamar"/></h3>

                            <p>Validation, filters, i18n</p>
								<span>
									<a href="https://vk.com/alexhamer" target="_blank">
                                        <i class="fa fa-vk"></i>
                                    </a>
									<a href="https://github.com/AlexHamer777" target="_blank">
                                        <i class="fa fa-github"></i>
                                    </a>
								</span>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="ch-item" style="background-image:url(media/images/team/oles.jpg)">
                        <div class="ch-info">
                            <h3><fmt:message key="aboutUs.olesLaba"/></h3>

                            <p>DAO, Connection</p>
								<span>
									<a href="https://www.facebook.com/oles.laba" target="_blank">
                                        <i class="fa fa-facebook"></i>
                                    </a>
									<a href="http://vk.com/olesko_laba" target="_blank">
                                        <i class="fa fa-vk"></i>
                                    </a>
									<a href="https://github.com/olesko" target="_blank">
                                        <i class="fa fa-github"></i>
                                    </a>
								</span>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="ch-item" style="background-image:url(media/images/team/andrew.jpg)">
                        <div class="ch-info">
                            <h3><fmt:message key="aboutUs.andrewProts"/></h3>

                            <p>Servlets, services</p>
								<span>
									<a href="https://www.facebook.com/andriy.prots.5" target="_blank">
                                        <i class="fa fa-facebook"></i>
                                    </a>
									<a href="http://vk.com/id6502713" target="_blank">
                                        <i class="fa fa-vk"></i>
                                    </a>
									<a href="https://github.com/AndyBoy1" target="_blank">
                                        <i class="fa fa-github"></i>
                                    </a>
								</span>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="ch-item" style="background-image:url(media/images/team/bohdan.jpg)">
                        <div class="ch-info">
                            <h3><fmt:message key="aboutUs.bohdanShchudlo"/></h3>

                            <p>Facebook API, Google Calendar</p>
								<span>
									<a href="https://www.facebook.com/shchudlobohdan" target="_blank">
                                        <i class="fa fa-facebook"></i>
                                    </a>
									<a href="http://vk.com/dartherohito" target="_blank">
                                        <i class="fa fa-vk"></i>
                                    </a>
									<a href="https://github.com/bohdanshchudlo" target="_blank">
                                        <i class="fa fa-github"></i>
                                    </a>
								</span>
                        </div>
                    </div>
                </li>
            </ul>
            <div id="github-text" class="text-center">
                <p class="lead">All code available on GitHub</p>
                <a href="https://github.com/code-busters/mosby" target="_blank">
                    <i class="fa fa-github"></i>
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
<script src="js/bootstrap.min.js"></script>

<script src="js/application.js"></script>

</body>

</html>
