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
    <title><fmt:message key="error.error"/> - Mosby - <fmt:message key="title"/></title>
    <link rel="shortcut icon" href="media/images/favicon.ico">
    <link rel="icon" type="image/png" href="media/images/favicon.png" />
    <meta name="description" content="Mosby - make it simple. New event management system" />

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">


    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="container-fluid error-page">
    <div class="row display-error">
        <div class="col-md-6">
            <div class="col-md-12">
                <a class="navbar-brand" href="<c:url value="/index"/>"></a>
            </div>
            <p><fmt:message key="error.ohNoSomethingHasGoneWrong"/>...</p>
            <label for="search"><fmt:message key="error.maybeTrySearchingForSomething"/>?</label>
            <div class="sb-search">
                <form action="search" method="GET">
                    <input class="sb-search-input" placeholder="<fmt:message key="error.enterYourSearchTerm"/>..." type="search" value="" name="search" id="search">
                    <input class="sb-search-submit" type="submit" value="search">
                    <span class="sb-icon-search"></span>
                </form>
            </div>
        </div>
    </div>

</div>
</body>

</html>
