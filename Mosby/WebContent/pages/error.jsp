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
                <a class="navbar-brand" href="#"></a>
            </div>
            <p>Oh no! Something has gone wrong...</p>
            <label for="search">Maybe try searching for something?</label>
            <div class="sb-search">
                <form action="search" method="GET">
                    <input class="sb-search-input" placeholder="Enter your search term..." type="search" value="" name="search" id="search">
                    <input class="sb-search-submit" type="submit" value="search">
                    <span class="sb-icon-search"></span>
                </form>
            </div>
        </div>
    </div>

</div>
</body>

</html>
