<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Create event - Mosby - event management</title>
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
        <h4>Create event for free</h4>
    </div>
    <div class="on-background-block col-md-10 col-md-offset-1">
        <div class="hidden-lg hidden-md">
            <h4>Create event for free</h4>
        </div>
        <div class="errors">
            <c:forEach items="${errors}" var="error">
                <p>${error}</p>
            </c:forEach>
        </div>
        <form enctype="multipart/form-data" action="createEvent" method="post" id="create-event-form">
            <h5>Event Detail</h5>

            <div class="form-group">
                <label for="event-name">Event Name</label>
                <input type="text" class="form-control" placeholder="Choose Event Name" name="event_name"
                       id="event-name"
                       required/>
            </div>
            <div class="form-group">
                <span class="as-label">Event Logo</span>
                <input type="file" class="hide" name="event_logo" id="open-logo" accept="image/*" />
                <label for="open-logo">
                    <span id="backup-img" class="hide">media/images/events/logo/default.png</span>
                    <img class="logo" src="media/images/events/logo/default.png">
                </label>
                <span class="change-img-name"></span>
                <span class="additional-input-info">Click on image to browse your logo</span>
            </div>
            <div class="form-group">
                <label for="event-background">Event Background</label>

                <div class="input-group">
							<span class="input-group-btn">
								<span class="btn btn-primary btn-file">
									Open
									<input type="file" name="event_background" id="event-background" accept="image/*"/>
								</span>
							</span>
                    <input type="text" class="form-control" readonly="" disabled="disabled">
                </div>
                <span class="additional-input-info">Select image with big resolution for better result</span>
            </div>
            <div class="form-group">
                <label for="datepicker-start">Start Date &amp; Time</label>

                <div class="input-prepend input-datepicker">
                    <button type="button" class="btn">
                        <span class="fui-calendar"></span>
                    </button>
                    <input type="text" placeholder="Start date" name="start_date" id="datepicker-start" readonly=""
                           required>
                </div>
                <input type="time" class="form-control time" value="00:00" name="start_time" required>
            </div>
            <div class="form-group">
                <label for="datepicker-end">End Date &amp; Time</label>

                <div class="input-prepend input-datepicker">
                    <button type="button" class="btn">
                        <span class="fui-calendar"></span>
                    </button>
                    <input type="text" placeholder="End date" name="end_date" id="datepicker-end" readonly="" required>
                </div>
                <input type="time" class="form-control time" value="00:00" name="end_time" required>
            </div>


            <div class="form-group">
                <label for="event-category">Category</label>
                <select name="event_category" class="select-block" id="event-category" form="create-event-form">
                    <option value="-1">
                        Select category...
                    </option>
                    <c:forEach items="${eventCategories}" var="category">
                        <option value="${category.id}">
                                ${category.category}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="event-type">Type</label>
                <select name="event_type" class="select-block" id="event-type" form="create-event-form">
                    <option value="-1">
                        Select type...
                    </option>
                    <c:forEach items="${eventTypes}" var="type">
                        <option value="${type.id}">
                                ${type.type}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="event-description">Event Description</label>
                <textarea rows="4" placeholder="Tell users about your event" class="form-control"
                          name="event_description"
                          id="event-description" form="create-event-form"></textarea>
            </div>
            <div class="form-group">
                <label for="event-location">Address</label>
                <input type="text" class="form-control" placeholder="Enter Address for your event" name="event_location"
                       id="event-location" onFocus="geolocate()"/>
            </div>
            <div class="form-group" id="googleMap" style="height:380px;"></div>

            <h5>Create Tickets</h5>

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
                    </div>
                </div>
                <!--END TICKETS-->
            </div>
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
                    </div>
                </div>
                <!--END PROMO CODES-->
            </div>
            <div class="col-md-3 col-sm-4 add-promo-code">
                <a id="add-promo-code" href="#fakelink" class="btn btn-block btn-lg btn-primary">
                    <span class="fui-plus"></span>Promo code</a>
            </div>

            <h5>Additional Settings</h5>

            <div class="form-group">
                <span class="as-label">Listing privacy</span>
                <label class="radio checked">
							<span class="icons">
								<span class="first-icon fui-radio-unchecked"></span>
								<span class="second-icon fui-radio-checked"></span>
							</span>
                    <input type="radio" name="privacy_event" id="public-event" value="public" data-toggle="radio">
                    Public event
                    <span class="additional">list this event on Eventbrite and search engines</span>
                </label>
                <label class="radio">
							<span class="icons">
								<span class="first-icon fui-radio-unchecked"></span>
								<span class="second-icon fui-radio-checked"></span>
							</span>
                    <input type="radio" name="privacy_event" id="private-event" value="private" data-toggle="radio">
                    Private event
                    <span class="additional">do not list this event publicly</span>
                </label>
            </div>

            <div class="col-md-4 col-md-offset-4">
                <button class="btn btn-primary btn-lg btn-block" name="submit" type="submit" value="Submit">
                    Create event
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

<script src="js/tickets-promoCodes.js"></script>
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

</body>

</html>