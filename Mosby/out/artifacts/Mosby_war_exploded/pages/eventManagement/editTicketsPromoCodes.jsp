<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Mosby - event management</title>
    <link rel="shortcut icon" href="media/images/favicon.ico">
    <link rel="icon" type="image/png" href="media/images/favicon.png"/>
    <meta name="description" content="Mosby - make it simple. New event management system"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

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
         style="background-image: url(media/images/bg_mask.png), url(media/images/events/background/${event.background})"></div>
</div>

<div id="wrapper" class="row user-profile">
<jsp:include page="parts/sideNavnar.jsp"/>
<div id="page-content-wrapper">
<div class="content-header">
    <h3>
        <a id="menu-toggle" href="#" class="btn btn-default">
            <span class="fui-list-columned"></span>
        </a>
        Edit event details
    </h3>
</div>
<div class="page-content inset">
<div class="errors">
    <c:forEach items="${errors}" var="error">
        <p>${error}</p>
    </c:forEach>
</div>
<form action="editTicketsCodes" method="post" id="edit-tickets-codes-form">
<h5>Tickets</h5>

<div class="form-group">
    <div id="tickets">
        <div class="row create-tickets-header hidden-xs">
            <div class="col-md-6 col-sm-6">Ticket name</div>
            <div class="col-md-2 col-sm-2">Quantity available</div>
            <div class="col-md-2 col-sm-2">Price</div>
            <div class="col-md-2 col-sm-2 text-center">Actions</div>
        </div>
        <div id="tickets-body">
            <input class="hide" type="text" value="" name="tickets_id"/>
            <c:forEach items="${tickets}" var="ticket">
                <div id="${ticket.id}" class="row">
                    <div class="col-md-6 col-sm-6">
                        <label class="visible-xs" for="event-ticket-name-${ticket.id}">Ticket
                            name</label>
                        <input type="text" class="form-control"
                               placeholder="Examples: Early Bird, VIP, Press"
                               name="event_ticket_name_${ticket.id}" value="${ticket.name}"
                               id="event-ticket-name-${ticket.id}"/>
                    </div>
                    <div class="col-md-2 col-sm-2">
                        <label class="visible-xs" for="event-ticket-quantity-${ticket.id}">Quantity
                            available</label>
                        <input type="number" class="form-control" placeholder="100"
                               name="event_ticket_quantity_${ticket.id}" value="${ticket.maxNumber}"
                               id="event-ticket-quantity-${ticket.id}" min="1"/>
                    </div>
                    <div class="col-md-2 col-sm-2">
                        <label class="visible-xs"
                               for="event-ticket-price-${ticket.id}">Price</label>
                        <input type="number" class="form-control" placeholder="0"
                               name="event_ticket_price_${ticket.id}" value="${ticket.price}"
                               id="event-ticket-price-${ticket.id}"
                               min="0"/>
                    </div>
                    <div class="col-md-2 col-sm-2 actions text-center">
                        <label class="visible-xs text-left">Ticket name</label>
                        <a href="#" data-toggle="modal" data-target="#my-modal-${ticket.id}">
                            <span class="fui-gear"></span>
                        </a>
                        <a class="delete-nearby-row" href="#">
                            <span class="fui-trash"></span>
                        </a>
                    </div>
                    <div class="modal fade" id="my-modal-${ticket.id}" tabindex="-1" role="dialog"
                         aria-labelledby="my-modal-label-${ticket.id}" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="my-modal-label-${ticket.id}">Ticket
                                        Settings</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="ticket-description-${ticket.id}">Ticket
                                            Description</label>
                                        <textarea rows="3"
                                                  placeholder="Additional info about ticket"
                                                  class="form-control"
                                                  id="ticket-description-${ticket.id}"
                                                  name="ticket_description_${ticket.id}"
                                                  value="${ticket.description}"
                                                  form="edit-tickets-codes-form"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="ticket-datepicker-start-${ticket.id}">Start Date &amp; Time For
                                            Ticket</label>
                                        <input type="date" class="form-control" placeholder="Start date for ticket"
                                               name="ticket_start_date_${ticket.id}"
                                               value="${ticket.startDate}" id="ticket-datepicker-start-${ticket.id}">
                                        <input type="time" class="form-control time" value="${ticket.startTime}"
                                               name="ticket_start_time_${ticket.id}">
                                    </div>
                                    <div class="form-group">
                                        <label for="ticket-datepicker-end-${ticket.id}">End Date &amp; Time For
                                            Ticket</label>
                                        <input type="date" class="form-control" placeholder="End date for ticket"
                                               name="ticket_end_date_${ticket.id}"
                                               value="${ticket.endDate}" id="ticket-datepicker-end-${ticket.id}">
                                        <input type="time" class="form-control time" value="${ticket.endTime}"
                                               name="ticket_end_time_${ticket.id}">
                                    </div>
                                    <div class="form-group">
                                        <span>Tickets Allowed Per Order</span>
                                        <label class="additional"
                                               for="ticket-min-per-order-${ticket.id}">Min</label>
                                        <input type="number" class="form-control" placeholder="1"
                                               value="1" name="ticket_min_per_order_${ticket.id}" value=""
                                               id="ticket-min-per-order-${ticket.id}" min="1"/>
                                        <label class="additional"
                                               for="ticket-max-per-order-${ticket.id}">Max</label>
                                        <input type="number" class="form-control" placeholder="1"
                                               name="ticket_max_per_order_${ticket.id}" value=""
                                               id="ticket-max-per-order-${ticket.id}" min="1"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <!--END TICKETS-->
</div>
<div class="row">
    <div class="col-md-3 col-sm-4 add-ticket">
        <a id="free-ticket" href="#fakelink" class="btn btn-block btn-lg btn-primary">
            <span class="fui-plus"></span>Free ticket</a>
    </div>
    <div class="col-md-3 col-sm-4 add-ticket">
        <a id="paid-ticket" href="#fakelink" class="btn btn-block btn-lg btn-primary">
            <span class="fui-plus"></span>Paid ticket</a>
    </div>
    <div class="col-md-3 col-sm-4 add-ticket">
        <a id="donation-ticket" href="#fakelink" class="btn btn-block btn-lg btn-default">
            <span class="fui-plus"></span>Donation</a>
    </div>
</div>

<h5>Promo codes</h5>

<div class="form-group">
    <div id="promo-codes">
        <div class="row promo-codes-header hidden-xs">
            <div class="col-md-6 col-sm-6">Promo code</div>
            <div class="col-md-2 col-sm-2">Quantity available</div>
            <div class="col-md-2 col-sm-2">Discount</div>
            <div class="col-md-2 col-sm-2 text-center">Actions</div>
        </div>
        <div id="promo-codes-body">
            <input class="hide" type="text" value="" name="promo_codes_id"/>
            <c:forEach items="${promoCodes}" var="promoCode">
                <div id="${promoCode.id}" class="row">
                    <div class="col-md-6 col-sm-6">
                        <label class="visible-xs" for="promo-code-code-${promoCode.id}">Promo code</label>
                        <input type="text" class="form-control"
                               placeholder="Examples: #Event_code32, R2D2"
                               name="promo_code_code_${promoCode.id}" value="${promoCode.code}"
                               id="promo-code-code-${promoCode.id}"/>
                    </div>
                    <div class="col-md-2 col-sm-2">
                        <label class="visible-xs" for="promo-code-quantity-${promoCode.id}">Quantity
                            available</label>
                        <input type="number" class="form-control" placeholder="5"
                               name="promo_code_quantity_${promoCode.id}" value="${promoCode.maxNumber}"
                               id="promo-code-quantity-${promoCode.id}" min="1"/>
                    </div>
                    <div class="col-md-2 col-sm-2">
                        <label class="visible-xs" for="promo-code-discount-${promoCode.id}">Discount</label>
                        <input type="number" class="form-control" placeholder="10%"
                               name="promo_code_discount_${promoCode.id}" value="${promoCode.discount}"
                               id="promo-code-discount-${promoCode.id}" min="0"/>
                    </div>
                    <div class="col-md-2 col-sm-2 actions text-center">
                        <label class="visible-xs text-left">Actions</label>
                        <a href="#" data-toggle="modal" data-target="#my-promo-modal-${promoCode.id}">
                            <span class="fui-gear"></span>
                        </a>
                        <a class="delete-nearby-row" href="#">
                            <span class="fui-trash"></span>
                        </a>
                    </div>
                    <div class="modal fade" id="my-promo-modal-${promoCode.id}" tabindex="-1" role="dialog"
                         aria-labelledby="my-promo-modal-label-${promoCode.id}" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="my-promo-modal-label-${promoCode.id}">Promo
                                        Code
                                        Settings</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="promo-code-description-${promoCode.id}">Promo Code
                                            Description</label>
                                        <textarea rows="3"
                                                  placeholder="Additional info about promo code"
                                                  class="form-control"
                                                  name="promo_code_description_${promoCode.id}"
                                                  value="${promoCode.description}"
                                                  id="promo-code-description-${promoCode.id}"
                                                  form="edit-tickets-codes-form"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <!--END PROMO CODES-->
</div>
<div class="row">
    <div class="col-md-3 col-sm-4 add-promo-code">
        <a id="add-promo-code" href="#fakelink" class="btn btn-block btn-lg btn-primary">
            <span class="fui-plus"></span>Promo code</a>
    </div>
</div>

<div class="col-md-4 col-md-offset-4">
    <button class="btn btn-primary btn-lg btn-block" name="submit" type="submit" value="Submit">
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
<script src="js/jquery.stacktable.js"></script>

<script src="js/tickets-promoCodes.js"></script>
<script src="js/application.js"></script>

</body>

</html>