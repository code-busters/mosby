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
                <form action="editEvent" enctype="multipart/form-data" method="post" id="edit-event-form">
                    <h5>Event Detail</h5>

                    <div class="form-group">
                        <label for="event-name">Event Name</label>
                        <input type="text" class="form-control" placeholder="Choose Event Name" value=""
                               name="event_name" id="event-name" required/>
                    </div>
                    <div class="form-group">
                        <span class="as-label">Event Logo</span>
                        <input type="file" class="hide" name="event_logo" id="open-event-logo" accept="image/*"/>
                        <label for="open-event-logo">
                            <c:choose>
                                <c:when test="${empty event.logo}">
                                    <span id="backup-img" class="hide">media/images/events/logo/default.png</span>
                                    <img class="event-logo" src="media/images/events/logo/default.png">
                                </c:when>
                                <c:otherwise>
                                    <span id="backup-img" class="hide">media/images/events/logo/${event.logo}</span>
                                    <img class="event-logo" src="media/images/events/logo/${event.logo}">
                                </c:otherwise>
                            </c:choose>
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
                            <span id="backup-img" class="hide">media/images/events/background/${event.background}</span>
                            <input type="text" class="form-control" value="${event.background}" readonly=""
                                   disabled="disabled">
                        </div>
                        <span class="additional-input-info">Select image with big resolution for better result</span>
                    </div>
                    <div class="form-group">
                        <label for="datepicker-start">Start Date &amp; Time</label>

                        <div class="input-prepend input-datepicker">
                            <button type="button" class="btn">
                                <span class="fui-calendar"></span>
                            </button>
                            <input type="text" placeholder="Start date" name="start_date" value="${event.startDate}"
                                   id="datepicker-start"
                                   readonly="" required>
                        </div>
                        <input type="time" class="form-control time" value="${event.startTime}" name="start_time"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="datepicker-end">End Date &amp; Time</label>

                        <div class="input-prepend input-datepicker">
                            <button type="button" class="btn">
                                <span class="fui-calendar"></span>
                            </button>
                            <input type="text" placeholder="End date" name="end_date" value="${event.endDate}"
                                   id="datepicker-end"
                                   readonly="" required>
                        </div>
                        <input type="time" class="form-control time" value="${event.endTime}" name="end_time" required>
                    </div>
                    <div class="form-group">
                        <label for="event-category">Category</label>
                        <select name="event_category" class="select-block" id="event-category" form="edit-event-form">
                            <c:set var="eventCategory" value="${event.eventCategory.category}"/>
                            <c:forEach items="${eventCategories}" var="category">
                                <c:set var="tempCategory" value="${category.category}"/>
                                <option value="${category.id}" >
                                        <%--<c:if test="${eventCategory == tempCategory"> selected </c:if> >--%>
                                        ${category.category}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="event-type">Type</label>
                        <select name="event_type" class="select-block" id="event-type" form="edit-event-form">
                            <c:set var="eventType" value="${event.eventType.type}"/>
                            <c:forEach items="${eventTypes}" var="type">
                                <c:set var="tempType" value="${type.type}"/>
                                <option value="${type.id}"
                                        <%--<c:if test="${eventType == tempType"> selected </c:if> >--%>
                                        ${type.type}>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="event-description">Event Description</label>
                        <textarea rows="4" placeholder="Tell users about your event" class="form-control"
                                  name="event_description" value="${event.description}" id="event-description"
                                  form="edit-event-form"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="event-location">Address</label>
                        <input type="text" class="form-control" placeholder="Enter Address for your event"
                               value="${event.location}" name="event_location" id="event-location"
                               onFocus="geolocate()"/>
                    </div>
                    <div class="form-group" id="googleMap" style="height:380px;"></div>

                    <h5>Additional Settings</h5>

                    <div class="form-group">
                        <span class="as-label">Listing privacy</span>
                        <label class="radio checked">
							<span class="icons">
								<span class="first-icon fui-radio-unchecked"></span>
								<span class="second-icon fui-radio-checked"></span>
							</span>
                            <input type="radio" name="privacy_event" id="public-event" value="public"
                                   data-toggle="radio">Public event
                            <span class="additional">list this event on Eventbrite and search engines</span>
                        </label>
                        <label class="radio">
							<span class="icons">
								<span class="first-icon fui-radio-unchecked"></span>
								<span class="second-icon fui-radio-checked"></span>
							</span>
                            <input type="radio" name="privacy_event" id="private-event" value="private"
                                   data-toggle="radio">Private event
                            <span class="additional">do not list this event publicly</span>
                        </label>
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

<!--	AIzaSyD548jnqtWftyB35lh_iMInJQhedC1XRc8   -->
<!--		<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyD548jnqtWftyB35lh_iMInJQhedC1XRc8&sensor=true&libraries=places"></script>-->
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true&libraries=places"></script>
<script src="js/geocoding.js"></script>

<script src="js/application.js"></script>

<script type="text/javascript">
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("active");
    });
</script>

</body>

</html>