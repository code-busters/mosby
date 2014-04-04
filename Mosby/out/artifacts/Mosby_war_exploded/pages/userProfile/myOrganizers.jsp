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
    <title><fmt:message key="myOrganizers.myOrganizers"/> - Mosby - <fmt:message key="title"/></title>
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
                    <fmt:message key="myOrganizers.myOrganizers"/>
                </h1>
            </div>
            <!-- Keep all page content within the page-content inset div! -->
            <div class="page-content inset">
                <div class="row">
                    <form action="updateOrganizer" enctype="multipart/form-data" method="post" id="organizers-form">
                        <div id="organizers">
                            <div class="row organizers-header hidden-xs">
                                <div class="col-md-6 col-sm-6"><fmt:message key="myOrganizers.organizer"/></div>
                                <div class="col-md-2 col-sm-2"><fmt:message key="myOrganizers.email"/></div>
                                <div class="col-md-2 col-sm-2 text-center"><fmt:message key="myOrganizers.actions"/></div>
                            </div>
                            <div id="organizers-body">
                                <c:forEach items="${organizers}" var="organizer">
                                    <div id="${organizer.id}" class="row">
                                        <div class="col-md-6 col-sm-6">
                                            <span class="as-label visible-xs"><fmt:message key="myOrganizers.organizer"/>*</span>
                                            <a href="<c:url value="/organizerPage"/>?id=${organizer.id}">${organizer.name}</a>
                                        </div>
                                        <div class="col-md-2 col-sm-2">
                                            <span class="as-label visible-xs"><fmt:message key="myOrganizers.email"/>*</span>

                                            <p>${organizer.email}</p>
                                        </div>
                                        <div class="col-md-2 col-sm-2 actions text-center">
                                            <label class="visible-xs text-left"><fmt:message key="myOrganizers.actions"/></label>
                                            <a id="open-falldown" href="#fakelink">
                                                <span class="fui-new"></span>
                                            </a>
                                            <a href="<c:url value="/myOrganizers?delete=${organizer.id}"/>">
                                                <span class="fui-trash"></span>
                                            </a>
                                        </div>
                                        <div id="falldown-${organizer.id}" class="falldown col-md-12">
											<input type="hidden" name="organizerId" value="${organizer.id}" />
                                            <div class="form-group">
                                                <label for="open-logo-${organizer.id}"><fmt:message key="myOrganizers.logo"/></label>
                                                <input type="file" class="hide" name="logo_${organizer.id}"
                                                       id="open-logo-${organizer.id}" accept="image/*"/>
                                                <label for="open-logo-${organizer.id}">
                                                    <span id="backup-img" class="hide">media/images/organizers/${organizer.logo}</span>
                                                    <img class="logo" src="media/images/organizers/${organizer.logo}">
                                                </label>
                                                <span class="change-img-name"></span>
                                                <span class="additional-input-info"><fmt:message key="myOrganizers.clickOnImageToBrowseYourLogo"/></span>
                                            </div>
                                            <div class="form-group">
                                                <label for="name-${organizer.id}"><fmt:message key="myOrganizers.name"/></label>
                                                <input class="form-control" placeholder="<fmt:message key="myOrganizers.name"/>"
                                                       name="name_${organizer.id}" id="name-${organizer.id}" value="${organizer.name}" required/>
                                            </div>
                                            <div class="form-group">
                                                <label for="email-${organizer.id}"><fmt:message key="myOrganizers.contactEmail"/></label>
                                                <input class="form-control" type="email" placeholder="<fmt:message key="myOrganizers.contactEmail"/>"
                                                       name="email_${organizer.id}" pattern="[^ @]*@[^ @]*\.[^ @]{2,}"
                                                       id="email-${organizer.id}" value="${organizer.email}" required/>
                                            </div>
                                            <div class="form-group">
                                                <label for="phone-${organizer.id}"><fmt:message key="myOrganizers.organizersPhone"/></label>
                                                <input class="form-control typeahead" placeholder="<fmt:message key="myOrganizers.organizersPhone"/>"
                                                       name="phone_${organizer.id}" id="phone-${organizer.id}" value="${organizer.phone}"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="about-${organizer.id}"><fmt:message key="myOrganizers.about"/></label>
                                                <textarea rows="4" placeholder="<fmt:message key="myOrganizers.tellAboutOrganizer"/>"
                                                          class="form-control" name="about_${organizer.id}"
                                                          id="about-${organizer.id}" form="organizers-form">${organizer.about}</textarea>
                                            </div>
                                            <div class="form-group">
                                                <label for="website-${organizer.id}"><fmt:message key="myOrganizers.website"/></label>
                                                <input type="url" class="form-control"
                                                       placeholder="<fmt:message key="myOrganizers.enterOrganizersWebsiteOrBlog"/>"
                                                       name="website_${organizer.id}" value="${organizer.site}" id="website-${organizer.id}"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="google-plus-${organizer.id}">Google+</label>
                                                <input type="url" class="form-control" placeholder="Google+"
                                                       name="google_plus_${organizer.id}"
                                                       value="${organizer.googlePlus}" id="google-plus-${organizer.id}"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="facebook-${organizer.id}">Facebook</label>
                                                <input type="url" class="form-control" placeholder="Facebook"
                                                       name="facebook_${organizer.id}" value="${organizer.facebook}" id="facebook-${organizer.id}"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="twitter-${organizer.id}">Twitter</label>
                                                <input type="url" class="form-control" placeholder="Twitter"
                                                       name="twitter_${organizer.id}" value="${organizer.twitter}" id="twitter-${organizer.id}"/>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-3 add-button">
                            <a id="free-ticket" href="<c:url value="/createOrganizer"/>"
                               class="btn btn-block btn-lg btn-primary">
                                <span class="fui-plus"></span><fmt:message key="myOrganizers.organizer"/></a>
                        </div>
                        <div id="save-button" class="col-md-2 col-sm-3 pull-right add-button hide">
                            <button class="btn btn-info btn-lg btn-block" name="submit" type="submit" value="Submit">
                                <fmt:message key="myOrganizers.save"/>
                            </button>
                        </div>
                    </form>
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