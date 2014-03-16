<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Mosby - event management</title>
    <link rel="shortcut icon" href="media/images/favicon.ico">
    <link rel="icon" type="image/png" href="media/images/favicon.png"/>
    <meta name="description" content="Mosby - make it simple. New event management system"/>

    <meta name="viewport" content="width=1000, initial-scale=1.0, maximum-scale=1.0">

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">
    <link href="css/pro-features.css" rel="stylesheet">
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
             style="background-image: url(media/images/bg_mask.png), url(media/images/default/${event.background})"></div>
    </div>

    <div id="wrapper" class="row user-profile">
        <jsp:include page="parts/sideNavnar.jsp"/>
        <div id="page-content-wrapper">
            <div class="content-header">
                <h3>
                    <a id="menu-toggle" href="#" class="btn btn-default">
                        <span class="fui-list-columned"></span>
                    </a>
                    Table of registered users
                </h3>
            </div>
            <div class="page-content inset">
                <form action="registeredTable" method="post" id="registered-table-form">
                    <div class="row registered-table">
                        <div class="table-responsive">
                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>
                                        <label class="checkbox no-label toggle-all" for="checkbox-table-1">
                                            <input type="checkbox" value="" id="checkbox-table-1"
                                                   data-toggle="checkbox">
                                        </label>
                                    </th>
                                    <th>#</th>
                                    <th>Checked</th>
                                    <th>User</th>
                                    <th>Ticket</th>
                                    <th>Time of purchase</th>
                                    <th>Promo-code</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tickets}" var="ticket">
                                    <tr>
                                        <td>
                                            <label class="checkbox no-label" for="checkbox-table-${ticket.id}">
                                                <input type="checkbox" value="${ticket.id}" id="checkbox-table-${ticket.id}"
                                                       name="checked_tickets" data-toggle="checkbox">
                                            </label>
                                        </td>
                                        <td>${ticket.id}</td>
                                        <td>
                                            <div class="switch switch-square" data-on-label="<i class='fui-check'></i>"
                                                 data-off-label="<i class='fui-cross'></i>">
                                                <input type="checkbox" name="checked_in_tickets_${ticket.id}"/>
                                            </div>
                                        </td>
                                        <td>
                                            <a href="#">${ticket.user.firstName} ${ticket.user.lastName}</a>
                                        </td>
                                        <td>
                                            <a href="#">${ticket.ticketInfo.name}</a>
                                        </td>
                                        <td>${ticket.timeOfPurchase}</td>
                                        <td>
                                            <a href="#">${ticket.promoCode.code}</a>
                                        </td>
                                        <td class="text-center">
                                            <a class="delete-nearby-row" href="#">
                                                <span class="fui-trash"></span>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <button class="btn btn-info" name="check" type="submit" value="check">
                            Check
                        </button>
                        <button class="btn btn-danger" name="delete" type="submit" value="delete">
                            Delete
                        </button>
                        <button class="btn btn-primary pull-right" name="save" type="submit" value="save">
                            Save
                        </button>
                    </div>
                </form>
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
<script src="js/jquery.stacktable.js"></script>
<script src="http://vjs.zencdn.net/4.3/video.js"></script>

<script src="js/application.js"></script>
</body>

<script type="text/javascript">
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("active");
    });
</script>

</body>

</html>
