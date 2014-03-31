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
    <title><fmt:message key="contactInfo.contactInfo"/> - Mosby - <fmt:message key="title"/></title>
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
             style="background-image: url(media/images/bg_mask.png), url(media/images/default/holi-feast.jpg)"></div>
    </div>

    <div id="wrapper" class="row user-profile">
        <jsp:include page="parts/sideNavbar.jsp"/>
        <div id="page-content-wrapper">
            <div class="content-header">
                <h1>
                    <a id="menu-toggle" href="#" class="">
                        <span class="btn btn-default fui-list-numbered"></span>
                    </a>
                    <fmt:message key="contactInfo.contactInfo"/>
                </h1>
            </div>
            <!-- Keep all page content within the page-content inset div! -->
            <div class="page-content inset">
                <div class="row">
                    <div class="col-md-6">
                        <div class="errors">
                            <c:forEach items="${errors}" var="error">
                                <p>${error}</p>
                            </c:forEach>
                        </div>
                        <form action="contactInfo" enctype="multipart/form-data" method="post" id="contact-info-form">
                            <input type="hidden" name="language" value="${language}" />
                            <div class="form-group">
                                <% if (request.getSession().getAttribute("userType") == "common") { %>
                                <input type="file" class="hide" name="profile_img" id="open-profile-img"
                                       accept="image/*"/>

                                <p class="change-img-name"></p>
                                <label for="open-profile-img">
                                    <span id="backup-img" class="hide">media/images/users/${user.image}</span>

                                    <div class="flow-img user-profile-img"
                                         style="background-image: url(media/images/users/${user.image});"></div>
                                    <span class="change-img"><fmt:message key="contactInfo.changePhoto"/></span>
                                </label>
                                <% } else { %>
                                <div class="flow-img user-profile-img"
                                     style="background-image: url(${user.image});"></div>
                                <% }%>
                            </div>
                            <div class="form-group">
                                <label for="first-name"><fmt:message key="contactInfo.firstName"/></label>
                                <input class="form-control" placeholder="<fmt:message key="contactInfo.firstName"/>" value="${user.firstName}"
                                       name="first_name" id="first-name"/>
                            </div>
                            <div class="form-group">
                                <label for="last-name"><fmt:message key="contactInfo.lastName"/></label>
                                <input class="form-control" placeholder="<fmt:message key="contactInfo.lastName"/>" value="${user.lastName}"
                                       name="last_name" id="last-name"/>
                            </div>
                            <div class="form-group">
                                <label for="country"><fmt:message key="contactInfo.country"/></label>
                                <input class="form-control typeahead" placeholder="<fmt:message key="contactInfo.country"/>" value="${user.country}"
                                       name="country" id="country"/>
                            </div>
                            <div class="form-group">
                                <label for="city"><fmt:message key="contactInfo.city"/></label>
                                <input class="form-control" placeholder="<fmt:message key="contactInfo.city"/>" value="${user.city}" name="city"
                                       id="city"/>
                            </div>
                            <div class="form-group">
                                <label for="birthday"><fmt:message key="contactInfo.birthday"/></label>

                                <div class="input-prepend input-datepicker">
                                    <button type="button" class="btn">
                                        <span class="fui-calendar"></span>
                                    </button>
                                    <input type="text" placeholder="<fmt:message key="contactInfo.birthday"/>" value="${user.birthDate}"
                                           name="birthday" id="birthday" readonly="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="gender"><fmt:message key="contactInfo.gender"/></label>
                                <select class="select-block" name="gender" id="gender" form="contact-info-form">
                                    <option value="-1">
                                        <fmt:message key="contactInfo.pleaseSelectYourGender"/>
                                    </option>
                                    <option value="Male" <c:if test="${user.gender == 'Male'}"> selected </c:if>>
                                        <fmt:message key="contactInfo.male"/>
                                    </option>
                                    <option value="Female" <c:if test="${user.gender == 'Female'}"> selected </c:if>>
                                        <fmt:message key="contactInfo.female"/>
                                    </option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="website"><fmt:message key="contactInfo.website"/></label>
                                <input type="url" class="form-control" placeholder="<fmt:message key="contactInfo.enterYourWebsiteOrBlog"/>"
                                       value="${user.site}" name="website" id="website"/>
                            </div>
                            <div class="form-group">
                                <label for="about"><fmt:message key="contactInfo.about"/></label>
                                <textarea class="form-control" rows="4" placeholder="<fmt:message key="contactInfo.tellAboutYourself"/>"
                                          name="about" id="about"
                                          form="contact-info-form">${user.about}</textarea>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary btn-lg btn-block" name="submit" type="submit"
                                        value="Submit">
                                    <fmt:message key="contactInfo.save"/>
                                </button>
                            </div>
                        </form>
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

<script type="text/javascript">
    $(function () {
        var countries = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            limit: 5,
            prefetch: {
                url: 'data/countries.json',
                filter: function (list) {
                    return $.map(list, function (country) {
                        return {
                            name: country
                        };
                    });
                }
            }
        });

        countries.initialize();

        $('.typeahead').typeahead(null, {
            name: 'countries',
            displayKey: 'name',
            source: countries.ttAdapter()
        });

    });
</script>

</body>

</html>
