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
    <title><fmt:message key="login.logIn"/> - Mosby - <fmt:message key="title"/></title>
    <!--	<link rel="shortcut icon" href="media/images/favicon.ico">-->
    <link rel="icon" type="image/png" href="media/images/favicon.png" />
    <meta name="description" content="Mosby - make it simple. New event management system" />

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">
    <link href="css/pro-features.css" rel="stylesheet">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link href="css/bootstrap-social.css" rel="stylesheet">
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
        <div id="background-block" class="flow-img big-background" style="background-image: url(media/images/bg_mask.png), url(media/images/default/google-team-speak.jpg)"></div>
    </div>

    <div class="row">
        <div class="on-background-block-header col-md-10 col-md-offset-1 hidden-xs">
            <h4><fmt:message key="login.logIn"/></h4>
        </div>
        <div class="on-background-block login col-md-6 col-md-offset-1">
            <div class="hidden-lg hidden-md">
                <h4><fmt:message key="login.logIn"/></h4>
            </div>
            <div class="errors">
                <c:forEach items="${errors}" var="error">
                    <p>${error}</p>
                </c:forEach>
            </div>
            <form action="login" method="post" id="login-form">
                <input type="hidden" name="language" value="${language}" />
                <div class="form-group">
                    <input class="form-control" type="email" placeholder="<fmt:message key="login.email"/>" name="email" pattern="[^ @]*@[^ @]*\.[^ @]{2,}" required id="email" />
                    <label class="login-field-icon fui-mail" for="email"></label>
                </div>
                <div class="form-group">
                    <input class="form-control" type="password" placeholder="<fmt:message key="login.password"/>" name="password" required id="password" />
                    <label class="login-field-icon fui-lock" for="password"></label>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary btn-lg btn-block" name="submit" type="submit" value="Submit">
                        <fmt:message key="login.submit"/>
                    </button>
                    <a class="login-link" href="<c:url value="/signUp"/>">
                        <fmt:message key="login.loginWith"/>:
                    </a>
                </div>
            </form>
            <div class="text-center">
                <a class="btn btn-social-icon btn-facebook" href="${facebookURL}">
                    <i class="fa fa-facebook"></i>
                </a>
                <div id="google-plus-button" class="hide">
                    <button class="g-signin"
                            data-scope="https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/userinfo.email"
                            data-requestvisibleactions="http://schemas.google.com/AddActivity"
                            data-clientId="${clientId}"
                            data-accesstype="offline"
                            data-callback="onSignInCallback"
                            data-theme="dark"
                            data-cookiepolicy="single_host_origin">
                    </button>
                </div>
                <a id="google-plus-wrapper" class="btn btn-social-icon btn-google-plus" href="#fakelink">
                    <i class="fa fa-google-plus"></i>
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
<script src="js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/jquery.ui.touch-punch.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-select.js"></script>
<script src="js/bootstrap-switch.js"></script>
<script src="js/flatui-checkbox.js"></script>
<script src="js/flatui-radio.js"></script>
<script src="js/jquery.tagsinput.js"></script>
<script src="js/jquery.placeholder.js"></script>

<script src="js/application.js"></script>
<script type="text/javascript">
    (function () {
        var po = document.createElement('script');
        po.type = 'text/javascript';
        po.async = true;
        po.src = 'https://plus.google.com/js/client:plusone.js';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(po, s);
    })();
    $(document).ready(function () {
        $('#disconnect').click(helper.disconnectServer);
        if ($('[data-clientid="YOUR_CLIENT_ID"]').length > 0) {
            alert('This sample requires your OAuth credentials (client ID) ' +
                            'from the Google APIs console:\n' +
                            '    https://code.google.com/apis/console/#:access\n\n' +
                            'Find and replace YOUR_CLIENT_ID with your client ID and ' +
                            'YOUR_CLIENT_SECRET with your client secret in the project sources.'
            );
        }
    });
    function onSignInCallback(authResult) {
        if (authResult['access_token']) {
            $.ajax({
                type: 'POST',
                url: 'socialSignUp?state=${state}',
                contentType: 'application/octet-stream; charset=utf-8',
                success: function () {
                    window.location.href = 'index';
                }, sData: false,
                data: authResult.code
            });
        }
    }
</script>
</body>

</html>