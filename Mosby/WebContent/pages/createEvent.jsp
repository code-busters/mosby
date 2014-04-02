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
    <title><fmt:message key="createEvent.createEvent"/> - Mosby - <fmt:message key="title"/></title>
    <link rel="shortcut icon" href="media/images/favicon.ico">
    <link rel="icon" type="image/png" href="media/images/favicon.png"/>
    <meta name="description" content="Mosby - make it simple. New event management system"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Loading Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">
    <link href="css/pro-features.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body onload="initialize()">
<div class="container-fluid">
<div class="row">
    <jsp:include page="parts/navbar.jsp"/>
</div>

<div class="row" style="background: #000">
    <div id="background-block" class="flow-img big-background"
         style="background-image: url(media/images/bg_mask.png), url(media/images/default/concert-smoke.jpg)"></div>
</div>

<div class="row">
    <div class="on-background-block-header col-md-10 col-md-offset-1 hidden-xs">
        <h4><fmt:message key="createEvent.createEventForFree"/></h4>
    </div>
    <div class="on-background-block col-md-10 col-md-offset-1">
        <div class="hidden-lg hidden-md">
            <h4><fmt:message key="createEvent.createEventForFree"/></h4>
        </div>
        <div class="errors">
            <c:forEach items="${errors}" var="error">
                <p>${error}</p>
            </c:forEach>
        </div>
        <form enctype="multipart/form-data" action="createEvent" method="post" id="create-event-form">
            <h5><fmt:message key="createEvent.eventDetail"/></h5>
            <input type="hidden" name="language" value="${language}" />
            <div class="form-group">
                <label for="event-name"><fmt:message key="createEvent.eventName"/></label>
                <input value="${event_name}" type="text" class="form-control" placeholder="<fmt:message key="createEvent.chooseEventName"/>" name="event_name"
                       id="event-name"
                       required/>
            </div>
            <div class="form-group">
                <span class="as-label"><fmt:message key="createEvent.eventLogo"/></span>
                <input type="file" class="hide" name="event_logo" id="open-logo" accept="image/*" />
                <label for="open-logo">
                    <span id="backup-img" class="hide">media/images/events/logo/default.png</span>
                    <img class="logo" src="media/images/events/logo/default.png">
                </label>
                <span class="change-img-name"></span>
                <span class="additional-input-info"><fmt:message key="createEvent.clickOnImageToBrowseYourLogo"/></span>
            </div>
            <div class="form-group">
                <label for="event-background"><fmt:message key="createEvent.eventBackground"/></label>

                <div class="input-group">
							<span class="input-group-btn">
								<span class="btn btn-primary btn-file">
									<fmt:message key="createEvent.open"/>
									<input type="file" name="event_background" id="event-background" accept="image/*"/>
								</span>
							</span>
                    <input type="text" class="form-control" readonly="" disabled="disabled">
                </div>
                <span class="additional-input-info"><fmt:message key="createEvent.selectImageWithBigResolutionForBetterResult"/></span>
            </div>
            <div class="form-group">
                <label for="datepicker-start"><fmt:message key="createEvent.startDateTime"/></label>

                <div class="input-prepend input-datepicker">
                    <button type="button" class="btn">
                        <span class="fui-calendar"></span>
                    </button>
                    <input value="${start_date}" type="text" placeholder="<fmt:message key="createEvent.startDate"/>" name="start_date" id="datepicker-start" readonly=""
                           required>
                </div>
                <input type="time" class="form-control time" value="${start_time}" placeholder="00:00" name="start_time" required>
            </div>
            <div class="form-group">
                <label for="datepicker-end"><fmt:message key="createEvent.endDateTime"/></label>

                <div class="input-prepend input-datepicker">
                    <button type="button" class="btn">
                        <span class="fui-calendar"></span>
                    </button>
                    <input value="${end_date}" type="text" placeholder="<fmt:message key="createEvent.endDate"/>" name="end_date" id="datepicker-end" readonly="" required>
                </div>
                <input type="time" class="form-control time" value="${end_time}" placeholder="00:00" name="end_time" required>
            </div>


            <div class="form-group">
                <label for="event-category"><fmt:message key="createEvent.category"/></label>
                <select name="event_category" class="select-block" id="event-category" form="create-event-form">
                    <option value="-1">
                        <fmt:message key="createEvent.selectCategory"/>...
                    </option>
                    <c:set var="selectedCategory" value="${event_category}"/>
                    <c:forEach items="${eventCategories}" var="category">
                        <c:set var="tempCategory" value="${category.category}"/>
                        <option value="${category.id}"
                                <c:if test="${selectedCategory == tempCategory}"> selected </c:if>>
                                ${category.category}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="event-type"><fmt:message key="createEvent.type"/></label>
                <select name="event_type" class="select-block" id="event-type" form="create-event-form">
                    <option value="-1">
                        <fmt:message key="createEvent.selectType"/>...
                    </option>
                    <c:set var="selectedType" value="${event_type}"/>
                    <c:forEach items="${eventTypes}" var="type">
                        <c:set var="tempType" value="${type.type}"/>
                        <option value="${type.id}"
                                <c:if test="${selectedType == tempType}"> selected </c:if>>
                                ${type.type}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="event-description"><fmt:message key="createEvent.eventDescription"/></label>
                <textarea value="${event_description}" rows="4" placeholder="<fmt:message key="createEvent.tellUsersAboutYourEvent"/>" class="form-control"
                          name="event_description"
                          id="event-description" form="create-event-form">${event_description}</textarea>
            </div>
            <div class="form-group">
                <label for="event-location"><fmt:message key="createEvent.address"/></label>
                <input value="${event_location}" type="text" class="form-control" placeholder="<fmt:message key="createEvent.enterAddressForYourEvent"/>" name="event_location"
                       id="event-location" onFocus="geolocate()"/>
            </div>
            <div class="form-group" id="googleMap" style="height:380px;"></div>

            <h5><fmt:message key="createEvent.createTickets"/></h5>

            <div class="form-group">
                <div id="tickets">
                    <div class="row create-tickets-header hidden-xs">
                        <div class="col-md-6 col-sm-6"><fmt:message key="createEvent.ticketName"/></div>
                        <div class="col-md-2 col-sm-2"><fmt:message key="createEvent.quantityAvailable"/></div>
                        <div class="col-md-2 col-sm-2"><fmt:message key="createEvent.price"/></div>
                        <div class="col-md-2 col-sm-2 text-center"><fmt:message key="createEvent.actions"/></div>
                    </div>
                    <div id="tickets-body">
                        <input class="hide" type="text" value="" name="tickets_id"/>
                    </div>
                </div>
                <!--END TICKETS-->
            </div>
            <div class="col-md-3 col-sm-4 add-ticket">
                <a id="free-ticket" href="#fakelink" class="btn btn-block btn-lg btn-primary">
                    <span class="fui-plus"></span><fmt:message key="createEvent.freeTicket"/></a>
            </div>
            <div class="col-md-3 col-sm-4 add-ticket">
                <a id="paid-ticket" href="#fakelink" class="btn btn-block btn-lg btn-primary">
                    <span class="fui-plus"></span><fmt:message key="createEvent.paidTicket"/></a>
            </div>
            <div class="col-md-3 col-sm-4 add-ticket">
                <a id="donation-ticket" href="#fakelink" class="btn btn-block btn-lg btn-default">
                    <span class="fui-plus"></span><fmt:message key="createEvent.donation"/></a>
            </div>

            <h5><fmt:message key="createEvent.promoCodes"/></h5>

            <div class="form-group">
                <div id="promo-codes">
                    <div class="row promo-codes-header hidden-xs">
                        <div class="col-md-6 col-sm-6"><fmt:message key="createEvent.promoCode"/></div>
                        <div class="col-md-2 col-sm-2"><fmt:message key="createEvent.quantityAvailable"/></div>
                        <div class="col-md-2 col-sm-2"><fmt:message key="createEvent.discount"/></div>
                        <div class="col-md-2 col-sm-2 text-center"><fmt:message key="createEvent.actions"/></div>
                    </div>
                    <div id="promo-codes-body">
                        <input class="hide" type="text" value="" name="promo_codes_id"/>
                    </div>
                </div>
                <!--END PROMO CODES-->
            </div>
            <div class="col-md-3 col-sm-4 add-promo-code">
                <a id="add-promo-code" href="#fakelink" class="btn btn-block btn-lg btn-primary">
                    <span class="fui-plus"></span><fmt:message key="createEvent.promoCode"/></a>
            </div>

            <h5><fmt:message key="createEvent.additionalSettings"/></h5>

            <div class="form-group">
                <span class="as-label"><fmt:message key="createEvent.listingPrivacy"/></span>
                <label class="radio <c:if test="${privacy_event == '0'}"> checked </c:if>">
							<span class="icons">
								<span class="first-icon fui-radio-unchecked"></span>
								<span class="second-icon fui-radio-checked"></span>
							</span>
                    <input type="radio" name="privacy_event" id="public-event" value="0" data-toggle="radio">
                    <fmt:message key="createEvent.publicEvent"/>
                    <span class="additional"><fmt:message key="createEvent.listThisEventOnEventbriteAndSearchEngines"/></span>
                </label>
                <label class="radio <c:if test="${privacy_event == '1'}"> checked </c:if>">
							<span class="icons">
								<span class="first-icon fui-radio-unchecked"></span>
								<span class="second-icon fui-radio-checked"></span>
							</span>
                    <input type="radio" name="privacy_event" id="private-event" value="1" data-toggle="radio">
                    <fmt:message key="createEvent.privateEvent"/>
                    <span class="additional"><fmt:message key="createEvent.doNotListThisEventPublicly"/></span>
                </label>
            </div>
            <div class="form-group">
                <label for="organizer"><fmt:message key="createEvent.organizeBy"/></label>
                <select name="organizer" class="select-block" id="organizer" form="create-event-form">
                    <c:forEach items="${organizers}" var="organizer">
                        <option value="${organizer.id}"
                                <c:if test="${organizer.id == organizer}"> selected </c:if>>
                                ${organizer.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-md-4 col-md-offset-4">
                <button class="btn btn-primary btn-lg btn-block" name="submit" type="submit" value="Submit">
                    <fmt:message key="createEvent.createEvent"/>
                </button>
            </div>
        </form>
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

<!--	AIzaSyD548jnqtWftyB35lh_iMInJQhedC1XRc8   -->
<!--		<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyD548jnqtWftyB35lh_iMInJQhedC1XRc8&sensor=true&libraries=places"></script>-->
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true&libraries=places"></script>
<script src="js/geocoding.js"></script>

<script src="js/application.js"></script>

<script type="text/javascript">
    $(document).on("keypress", 'form', function (e) {
        var code = e.keyCode || e.which;
        if (code == 13) {
            e.preventDefault();
            return false;
        }
    });
</script>
<%@include file="parts/ticketsPromoCodes.jsp" %>
</body>

</html>