<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Mosby - event management</title>
    <link rel="shortcut icon" href="media/images/favicon.ico">
    <link rel="icon" type="image/png" href="media/images/favicon.png" />
    <meta name="description" content="Mosby - make it simple. New event management system" />

    <meta name="viewport" content="width=1000, initial-scale=1.0, maximum-scale=1.0">

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
        <div id="background-block" class="flow-img" style="background-image: url(../media/images/bg_mask.png), url(media/images/default/google-io-2012.jpg)"></div>
    </div>

    <div class="row">
        <div class="on-background-block-header col-md-10 col-md-offset-1">
            <h4>Sign up for free</h4>
        </div>
        <div class="on-background-block col-md-6 col-md-offset-1">
            <div class="hidden-lg hidden-md">
                <h4>Sign up for free</h4>
            </div>
            <form action="signUp" method="post" id="sign-up-form">
                <div class="form-group">
                    <input class="form-control" placeholder="First Name" name="first_name" required id="first-name" />
                    <label class="login-field-icon fui-user" for="first-name"></label>
                </div>
                <div class="form-group">
                    <input class="form-control" placeholder="Last Name" name="last_name" required id="last-name" />
                    <label class="login-field-icon fui-user" for="last-name"></label>
                </div>
                <div class="form-group">
                    <input class="form-control" type="email" placeholder="Email" name="email" pattern="[^ @]*@[^ @]*\.[^ @]{2,}" required id="email" />
                    <label class="login-field-icon fui-mail" for="email"></label>
                </div>
                <div class="form-group">
                    <input class="form-control" type="password" placeholder="Password" name="password" required id="password" />
                    <label class="login-field-icon fui-lock" for="password"></label>
                </div>
                <div class="form-group">
                    <input class="form-control" type="password" placeholder="Confirm Password" name="confirm-password" required id="confirm-password" />
                    <label class="login-field-icon fui-lock" for="password"></label>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary btn-lg btn-block" name="submit" type="submit" value="Submit">
                        Submit
                    </button>
                    <a class="login-link" href="#">
                        Already registered? Login here
                    </a>
                </div>
            </form>
        </div>
        <div class="other-sign-up col-md-4">
            <h4>Sign up with:</h4>
            <div class="col-md-10">
                <a class="btn btn-block btn-social btn-facebook" href="${facebookURL}">
                    <i class="fa fa-facebook"></i>Sign in with Facebook
                </a>
                <a class="btn btn-block btn-social btn-google-plus">
                    <i class="fa fa-google-plus"></i>Sign in with Google
                </a>
                <a class="btn btn-block btn-social btn-twitter">
                    <i class="fa fa-twitter"></i>Sign in with Twitter
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

<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/jquery.ui.touch-punch.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-select.js"></script>
<script src="js/bootstrap-switch.js"></script>
<script src="js/flatui-checkbox.js"></script>
<script src="js/flatui-radio.js"></script>
<script src="js/jquery.tagsinput.js"></script>
<script src="js/jquery.placeholder.js"></script>

<script src="js/application1.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("#background-block").css("height", 4 * $(window).height() / 5);
    });
    $(window).resize(function() {
        $("#background-block").css("height", 4 * $(window).height() / 5);
    });
</script>

</body>

</html>