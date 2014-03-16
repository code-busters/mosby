<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div id="background-block" class="flow-img" style="background-image: url(media/images/bg_mask.png), url(media/images/default/holi-feast.jpg)"></div>
</div>

<div id="wrapper" class="row user-profile">
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="<c:url value="/contactInfo"/>">YOUR ACCOUNT</a>
            </li>
            <li>
                <a href="<c:url value="/contactInfo"/>">Contact Info</a>
            </li>
            <li>
                <a href="#">Tickets</a>
            </li>
            <li>
                <a href="#">Overview</a>
            </li>
            <li>
                <a href="#">Events</a>
            </li>
            <li>
                <a href="#">About</a>
            </li>
            <li>
                <a href="#">Services</a>
            </li>
            <li>
                <a href="#">Contact</a>
            </li>
        </ul>
    </div>
    <div id="page-content-wrapper">
        <div class="content-header">
            <h1>
                <a id="menu-toggle" href="#" class="btn btn-default">
                    <span class="sr-only">Toggle navigation</span>
                </a>
                Contact info
            </h1>
        </div>
        <!-- Keep all page content within the page-content inset div! -->
        <div class="page-content inset">
            <div class="row">
                <div class="col-md-6">
                    <form action="contactInfo" method="post" id="contact-info-form">
                        <div class="form-group">
                            <input type="file" class="hide" name="profile-img" id="open-profile-img" accept="image/*" />
                            <p class="change-img-name"></p>
                            <label for="open-profile-img">
                                <span id="backup-profile-img" class="hide">media/images/users/${user.image}</span>
                                <div class="flow-img user-profile-img" style="background-image: url(media/images/users/${user.image});"></div>
                                <span class="change-img">Change photo</span>
                            </label>
                        </div>
                        <div class="form-group">
                            <label for="first-name">First Name</label>
                            <input class="form-control" placeholder="First Name" value="${user.firstName}" name="first_name" id="first-name" />
                        </div>
                        <div class="form-group">
                            <label for="last-name">Last Name</label>
                            <input class="form-control" placeholder="Last Name" value="${user.lastName}" name="last_name" id="last-name" />
                        </div>
                        <div class="form-group">
                            <label for="country">Country</label>
                            <input class="form-control typeahead" placeholder="Country" value="${user.country}" name="country" id="country" />
                        </div>
                        <div class="form-group">
                            <label for="city">City</label>
                            <input class="form-control" placeholder="City" value="${user.city}" name="city" id="city" />
                        </div>
                        <div class="form-group">
                            <label for="birthday">Birthday</label>
                            <div class="input-prepend input-datepicker">
                                <button type="button" class="btn">
                                    <span class="fui-calendar"></span>
                                </button>
                                <input type="text" placeholder="Your Birthday" value="${user.birthDate}" name="birthday" id="birthday" readonly="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="gender">Gender</label>
                            <select class="select-block" name="gender" id="gender" form="contact-info-form">
                                <option value="-1">
                                    Please select your gender
                                </option>
                                <option value="Male">
                                    Male
                                </option>
                                <option value="Female">
                                    Female
                                </option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="website">Website</label>
                            <input type="url" class="form-control" placeholder="Enter your website or blog" value="${user.site}" name="website" id="website" />
                        </div>
                        <div class="form-group">
                            <label for="about">About</label>
                            <textarea class="form-control" rows="4" placeholder="Tell about yourself" value="${user.about}" name="about" id="about" form="contact-info-form"></textarea>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-primary btn-lg btn-block" name="submit" type="submit" value="Submit">
                                Save
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
<script src="js/typeahead.bundle.min.js"></script>

<script src="js/application.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("#background-block").css("height", 1 * $(window).height() / 5);
    });
    $(window).resize(function() {
        $("#background-block").css("height", 1 * $(window).height() / 5);
    });
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("active");
    });
    $(function() {
        var countries = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            limit: 5,
            prefetch: {
                url: 'data/countries.json',
                filter: function(list) {
                    return $.map(list, function(country) {
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
