<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Mosby - event management</title>
    <link rel="shortcut icon" href="media/images/favicon.ico">
    <link rel="icon" type="image/png" href="media/images/favicon.png" />
    <meta name="description" content="Mosby - make it simple. New event management system" />

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
    <div id="background-block" class="flow-img" style="background-image: url(media/images/bg_mask.png), url(media/images/default/holi-feast.jpg)"></div>
</div>

<div id="wrapper" class="row user-profile">
    <jsp:include page="parts/sideNavnar.jsp"/>
    <div id="page-content-wrapper">
        <div class="content-header">
            <h1>
                <a id="menu-toggle" href="#" class="btn btn-default">
                    <span class="fui-list-columned"></span>
                </a>
                Create Organization
            </h1>
        </div>
        <!-- Keep all page content within the page-content inset div! -->
        <div class="page-content inset">
            <div class="row">
                <div class="col-md-6">
                    <form action="createOrganizer" enctype="multipart/form-data" method="post" id="organizers-form">
                        <div class="form-group">
                            <label for="open-logo">Logo</label>
                            <input type="file" class="hide" name="logo" id="open-logo" accept="image/*" />
                            <label for="open-logo">
                                <span id="backup-img" class="hide">media/images/organizers/default.png</span>
                                <img class="logo" src="media/images/organizers/default.png">
                            </label>
                            <span class="change-img-name"></span>
                            <span class="additional-input-info">Click on image to browse your logo</span>
                        </div>
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input class="form-control" placeholder="Name" name="name" id="name" required />
                        </div>
                        <div class="form-group">
                            <label for="email">Contact Email</label>
                            <input class="form-control" type="email" placeholder="Contact email" name="email" pattern="[^ @]*@[^ @]*\.[^ @]{2,}" id="email" required />
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input class="form-control typeahead" placeholder="Your phone" name="phone" id="phone" />
                        </div>
                        <div class="form-group">
                            <label for="about">About</label>
                            <textarea rows="4" placeholder="Tell about organization" class="form-control" name="about" id="about"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="website">Website</label>
                            <input type="url" class="form-control" placeholder="Enter organization's website or blog" name="website" id="website" />
                        </div>
                        <div class="form-group">
                            <label for="google-plus">Google+</label>
                            <input type="url" class="form-control" placeholder="Google+" name="google+plus" id="google-plus" />
                        </div>
                        <div class="form-group">
                            <label for="facebook">Facebook</label>
                            <input type="url" class="form-control" placeholder="Facebook" name="facebook" id="facebook" />
                        </div>
                        <div class="form-group">
                            <label for="twitter">Twitter</label>
                            <input type="url" class="form-control" placeholder="Twitter" name="twitter" id="twitter" />
                        </div>
                        <div class="form-group">
                            <button class="btn btn-primary btn-lg btn-block" name="submit" type="submit" value="Submit">
                                Create
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
    $(document).on("keypress", 'form', function(e) {
        var code = e.keyCode || e.which;
        if (code == 13) {
            e.preventDefault();
            return false;
        }
    });
</script>

</body>

</html>