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
    <title><fmt:message key="editTicketsPromoCodes.editTicketsPromoCodes"/> - Mosby - <fmt:message key="title"/></title>
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
<jsp:include page="parts/sideNavbar.jsp"/>
<div id="page-content-wrapper">
<div class="content-header">
    <h3>
        <a id="menu-toggle" href="#" class="btn btn-default">
            <span class="fui-list-columned"></span>
        </a>
        <fmt:message key="editTicketsPromoCodes.editEventDetails"/>
    </h3>
</div>
<div class="page-content inset">
<div class="errors">
    <c:forEach items="${errors}" var="error">
        <p>${error}</p>
    </c:forEach>
</div>
<form action="editTicketsPromoCodes" method="post" id="edit-tickets-codes-form">
<input type="hidden" name="eventId" value="${event.id}"/>
<h5><fmt:message key="editTicketsPromoCodes.tickets"/></h5>

<div class="form-group">
    <div id="tickets">
        <div class="row create-tickets-header hidden-xs">
            <div class="col-md-6 col-sm-6"><fmt:message key="editTicketsPromoCodes.ticketName"/></div>
            <div class="col-md-2 col-sm-2"><fmt:message key="editTicketsPromoCodes.quantityAvailable"/></div>
            <div class="col-md-2 col-sm-2"><fmt:message key="editTicketsPromoCodes.price"/></div>
            <div class="col-md-2 col-sm-2 text-center"><fmt:message key="editTicketsPromoCodes.actions"/></div>
        </div>
        <div id="tickets-body">
            <input class="hide" type="text" value="" name="tickets_id"/>
            <c:forEach items="${ticketsInfo}" var="ticket">
                <div id="${ticket.id}" class="row">
                    <input class="hide" type="text" name="ticket_id" value="${ticket.id}" >
                    <div class="col-md-6 col-sm-6">
                        <label class="visible-xs" for="event-ticket-name-${ticket.id}"><fmt:message key="editTicketsPromoCodes.ticketName"/></label>
                        <input type="text" class="form-control"
                               placeholder="<fmt:message key="editTicketsPromoCodes.examplesEarlyBirdVipPress"/>"
                               name="event_ticket_name_${ticket.id}" value="${ticket.name}"
                               id="event-ticket-name-${ticket.id}"/>
                    </div>
                    <div class="col-md-2 col-sm-2">
                        <label class="visible-xs" for="event-ticket-quantity-${ticket.id}"><fmt:message key="editTicketsPromoCodes.quantityAvailable"/></label>
                        <input type="number" class="form-control" placeholder="100"
                               name="event_ticket_quantity_${ticket.id}" value="${ticket.maxNumber}"
                               id="event-ticket-quantity-${ticket.id}" min="1"/>
                    </div>
                    <div class="col-md-2 col-sm-2">
                        <label class="visible-xs"
                               for="event-ticket-price-${ticket.id}"><fmt:message key="editTicketsPromoCodes.price"/></label>
                        <input type="number" class="form-control" placeholder="0"
                               name="event_ticket_price_${ticket.id}" value="${ticket.price}"
                               id="event-ticket-price-${ticket.id}"
                               min="0"/>
                    </div>
                    <div class="col-md-2 col-sm-2 actions text-center">
                        <label class="visible-xs text-left"><fmt:message key="editTicketsPromoCodes.ticketName"/></label>
                        <a id="open-falldown" href="#fakelink">
                            <span class="fui-new"></span>
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
                                    <h4 class="modal-title" id="my-modal-label-${ticket.id}"><fmt:message key="editTicketsPromoCodes.ticketSettings"/></h4>
                                </div>
                                <div class="modal-body">

                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="falldown-${ticket.id}" class="falldown col-md-12">
                        <div class="form-group">
                            <label for="ticket-description-${ticket.id}"><fmt:message key="editTicketsPromoCodes.ticketDescription"/></label>
                            <textarea rows="3"
                                      placeholder="<fmt:message key="editTicketsPromoCodes.additionalInfoAboutTicket"/>"
                                      class="form-control"
                                      id="ticket-description-${ticket.id}"
                                      name="ticket_description_${ticket.id}"
                                      form="edit-tickets-codes-form">${ticket.description}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="ticket-datepicker-start-${ticket.id}"><fmt:message key="editTicketsPromoCodes.startDateTimeForTicket"/></label>
                            <input type="date" class="form-control" placeholder="<fmt:message key="editTicketsPromoCodes.startDate"/>"
                                   name="ticket_start_date_${ticket.id}"
                                   value="${ticket.startDate}" id="ticket-datepicker-start-${ticket.id}">
                            <input type="time" class="form-control time" value="${ticket.startTime}"
                                   name="ticket_start_time_${ticket.id}">
                        </div>
                        <div class="form-group">
                            <label for="ticket-datepicker-end-${ticket.id}"><fmt:message key="editTicketsPromoCodes.endDateTimeForTicket"/></label>
                            <input type="date" class="form-control" placeholder="<fmt:message key="editTicketsPromoCodes.endDate"/>"
                                   name="ticket_end_date_${ticket.id}"
                                   value="${ticket.endDate}" id="ticket-datepicker-end-${ticket.id}">
                            <input type="time" class="form-control time" value="${ticket.endTime}"
                                   name="ticket_end_time_${ticket.id}">
                        </div>
                        <div class="form-group">
                            <span><fmt:message key="editTicketsPromoCodes.ticketsAllowedPerOrder"/></span>
                            <label class="additional"
                                   for="ticket-min-per-order-${ticket.id}"><fmt:message key="editTicketsPromoCodes.min"/></label>
                            <input type="number" class="form-control" placeholder="1"
                                   value="1" name="ticket_min_per_order_${ticket.id}" value=""
                                   id="ticket-min-per-order-${ticket.id}" min="1"/>
                            <label class="additional"
                                   for="ticket-max-per-order-${ticket.id}"><fmt:message key="editTicketsPromoCodes.max"/></label>
                            <input type="number" class="form-control" placeholder="1"
                                   name="ticket_max_per_order_${ticket.id}" value=""
                                   id="ticket-max-per-order-${ticket.id}" min="1"/>
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
            <span class="fui-plus"></span><fmt:message key="editTicketsPromoCodes.freeTicket"/></a>
    </div>
    <div class="col-md-3 col-sm-4 add-ticket">
        <a id="paid-ticket" href="#fakelink" class="btn btn-block btn-lg btn-primary">
            <span class="fui-plus"></span><fmt:message key="editTicketsPromoCodes.paidTicket"/></a>
    </div>
    <div class="col-md-3 col-sm-4 add-ticket">
        <a id="donation-ticket" href="#fakelink" class="btn btn-block btn-lg btn-default">
            <span class="fui-plus"></span><fmt:message key="editTicketsPromoCodes.donation"/></a>
    </div>
</div>

<h5><fmt:message key="editTicketsPromoCodes.promoCodes"/></h5>

<div class="form-group">
    <div id="promo-codes">
        <div class="row promo-codes-header hidden-xs">
            <div class="col-md-6 col-sm-6"><fmt:message key="editTicketsPromoCodes.promoCode"/></div>
            <div class="col-md-2 col-sm-2"><fmt:message key="editTicketsPromoCodes.quantityAvailable"/></div>
            <div class="col-md-2 col-sm-2"><fmt:message key="editTicketsPromoCodes.discount"/></div>
            <div class="col-md-2 col-sm-2 text-center"><fmt:message key="editTicketsPromoCodes.actions"/></div>
        </div>
        <div id="promo-codes-body">
            <input class="hide" type="text" value="" name="promo_codes_id"/>
            <c:forEach items="${promoCodes}" var="promoCode">
                <div id="${promoCode.id}" class="row">
                    <input class="hide" type="text" name="promo_code_id" value="${promoCode.id}">
                    <div class="col-md-6 col-sm-6">
                        <label class="visible-xs" for="promo-code-code-${promoCode.id}"><fmt:message key="editTicketsPromoCodes.promoCode"/></label>
                        <input type="text" class="form-control"
                               placeholder="<fmt:message key="editTicketsPromoCodes.examplesR2D2"/>"
                               name="promo_code_code_${promoCode.id}" value="${promoCode.code}"
                               id="promo-code-code-${promoCode.id}"/>
                    </div>
                    <div class="col-md-2 col-sm-2">
                        <label class="visible-xs" for="promo-code-quantity-${promoCode.id}"><fmt:message key="editTicketsPromoCodes.quantityAvailable"/></label>
                        <input type="number" class="form-control" placeholder="5"
                               name="promo_code_quantity_${promoCode.id}" value="${promoCode.maxNumber}"
                               id="promo-code-quantity-${promoCode.id}" min="1"/>
                    </div>
                    <div class="col-md-2 col-sm-2">
                        <label class="visible-xs" for="promo-code-discount-${promoCode.id}"><fmt:message key="editTicketsPromoCodes.discount"/></label>
                        <input type="number" class="form-control" placeholder="10"
                               name="promo_code_discount_${promoCode.id}" value="${promoCode.discount}"
                               id="promo-code-discount-${promoCode.id}" min="0"/>
                    </div>
                    <div class="col-md-2 col-sm-2 actions text-center">
                        <label class="visible-xs text-left"><fmt:message key="editTicketsPromoCodes.actions"/></label>
                        <a id="open-falldown" href="#fakelink">
                            <span class="fui-new"></span>
                        </a>
                        <a class="delete-nearby-row" href="#">
                            <span class="fui-trash"></span>
                        </a>
                    </div>
                    <div id="falldown-${promoCode.id}" class="falldown col-md-12">

                        <div class="form-group">
                            <label for="promo-code-description-${promoCode.id}"><fmt:message key="editTicketsPromoCodes.promoCodeDescription"/></label>
                            <textarea rows="3"
                                      placeholder="<fmt:message key="editTicketsPromoCodes.additionalInfoAboutPromoCode"/>"
                                      class="form-control"
                                      name="promo_code_description_${promoCode.id}"
                                      id="promo-code-description-${promoCode.id}"
                                      form="edit-tickets-codes-form">${promoCode.description}</textarea>
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
            <span class="fui-plus"></span><fmt:message key="editTicketsPromoCodes.promoCode"/></a>
    </div>
</div>

<div class="col-md-4 col-md-offset-4">
    <button class="btn btn-primary btn-lg btn-block" name="submit" type="submit" value="Submit">
        <fmt:message key="editTicketsPromoCodes.save"/>
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
<script src="js/jquery.stacktable.js"></script>

<script src="js/application.js"></script>
<%@include file="../parts/ticketsPromoCodes.jsp" %>
</body>

</html>