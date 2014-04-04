<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="main.java.com.mosby.i18n.text" />
<html>

<head>
<meta charset="utf-8">
<title><fmt:message key="search.search" /> - Mosby - <fmt:message
		key="title" /></title>
<link rel="shortcut icon" href="media/images/favicon.ico">
<link rel="icon" type="image/png" href="media/images/favicon.png" />
<meta name="description"
	content="Mosby - make it simple. New event management system" />

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

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
			<jsp:include page="parts/navbar.jsp" />
		</div>

		<div class="row" style="background: #000">
			<div id="background-block" class="flow-img big-background"
				style="background-image: url(media/images/bg_mask.png), url(media/images/default/search.jpg)"></div>
		</div>

		<div class="row">
			<div class="on-background-block-header col-md-10 col-md-offset-1">
				<h4>
					<fmt:message key="search.searchResults" />
				</h4>
			</div>
			<div
				class="on-background-block search-results col-md-8 col-md-offset-1">
				<div class="hidden-lg hidden-md">
					<h4>
						<fmt:message key="search.searchResults" />
					</h4>
				</div>
				<div id="sb-search" class="sb-search">
					<form action="search" method="POST">
						<input class="sb-search-input"
							placeholder="<fmt:message key="search.enterYourSearchTerm"/>..."
							type="search"
							value="<fmt:message key="search.enterYourSearchTerm"/>..."
							name="search" id="search"> <input
							class="sb-search-submit" type="submit" value="search"> <span
							class="sb-icon-search"></span> <a id="open-falldown-search"
							href="#fakelink"> <span class="sb-icon-gear"></span>
						</a>
						<div id="falldown" class="falldown col-md-12">
							<h6>
								<fmt:message key="search.adjustSearch" />
							</h6>
							<div class="form-group">
								<label for="event-category"><fmt:message
										key="search.category" /></label> <select name="event_category"
									class="select-block" id="event-category">
									<option value="-1">
										<fmt:message key="search.selectCategory" />...
									</option>
									<c:forEach items="${eventCategories}" var="category">
										<option value="${category.id}">${category.category}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="event-type"><fmt:message key="search.type" /></label>
								<select name="event_type" class="select-block" id="event-type">
									<option value="-1">
										<fmt:message key="search.selectType" />...
									</option>
									<c:forEach items="${eventTypes}" var="type">
										<option value="${type.id}">${type.type}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<span class="as-label"><fmt:message key="search.date" /></span>
								<div class="input-prepend input-datepicker">
									<button type="button" class="btn">
										<span class="fui-calendar"></span>
									</button>
									<input type="text"
										placeholder="<fmt:message key="search.startDate"/>"
										name="start_date" id="datepicker-start" readonly="">
								</div>
								<span>-</span>
								<div class="input-prepend input-datepicker">
									<button type="button" class="btn">
										<span class="fui-calendar"></span>
									</button>
									<input type="text"
										placeholder="<fmt:message key="search.endDate"/>"
										name="end_date" id="datepicker-end" readonly="">
								</div>
							</div>
							<div class="form-group">
								<span class="as-label"><fmt:message key="search.price" /></span>
								<input type="number" class="form-control price" placeholder="1"
									value="0" name="min_price" id="min-price" min="0" /> <input
									type="number" class="form-control price" placeholder="1"
									value="0" name="max_price" id="max-price" min="0" />
							</div>
						</div>
					</form>
				</div>
				<c:forEach items="${events}" var="event">
					<div class="event">
						<div class="event-image flow-img"
							style="background-image:url(media/images/events/background/${event.background})"></div>
						<div class="event-detail">
							<h2>${event.name}</h2>
							<ul>
								<li><span class="fui-calendar-solid"></span>
									${event.startDate} - ${event.endDate}</li>
								<li><span class="fui-time"></span> ${event.startTime} -
									${event.endTime}</li>
								<li><span class="fui-location"></span> ${event.location}</li>
							</ul>
							<p class="ellipsis-3">${event.description}</p>
							<div class="text-right read-more">
								<a href="<c:url value="/eventPage?eventId=${event.id}"/>"><fmt:message
										key="search.readMore" /> >></a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

		<div class="row">
			<jsp:include page="parts/footer.jsp" />
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
	<script src="js/jquery.ellipsis.js"></script>

	<script src="js/application.js"></script>

	<script type="text/javascript">
		$('.ellipsis-3').ellipsis({
			row : 3
		});
	</script>

</body>