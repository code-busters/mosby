<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default navbar-fixed-top nav-transparent" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
            <span class="sr-only">Toggle navigation</span>
        </button>
        <a class="navbar-brand" href="<c:url value="/index"/>"></a>
    </div>
    <div class="collapse navbar-collapse" id="navbar-collapse-01">
        <ul class="nav navbar-nav navbar-left">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    Categories
                    <b class="caret"></b>
                </a>
                <span class="dropdown-arrow"></span>
                <ul class="dropdown-menu">
                    <c:forEach items="${eventCategories}" var="category">
                    <li>
                        <a href="#">${category.category}</a>
                    </li>
                    </c:forEach>
                    <li class="divider"></li>
                    <li>
                        <a href="#">All</a>
                    </li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <% if (request.getSession().getAttribute("user") != null) { %>
            <li>
                <div id="user-open" class="flow-img nav-user-img" style="background-image: url(media/images/users/${user.userProfile.image});"></div>
            </li>
            <div id="user-settings" class="col-md-3 hide">
                <div class="col-md-4">
                    <div class="flow-img user-settings-user-img" style="background-image: url(media/images/users/${user.userProfile.image});"></div>
                </div>
                <div class="col-md-7 col-md-offset-1">
                    <h4>${user.firstName} ${user.lastName}
                        <span>${user.email}</span>
                    </h4>
                    <a href="<c:url value="/profilePage"/>">View profile</a>
                    <a href="<c:url value="/createEvent"/>">Create event</a>
                </div>
                <div class="col-md-12 bottom-settings">
                    <a href="<c:url value="/index?logout=${user.id}"/>" class="btn btn-primary pull-right">Log Out</a>
                </div>
            </div>
            <% } else {%>
            <li>
                <a href="<c:url value="/login"/>">Login</a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="<c:url value="/signUp"/>">Sign up</a>
            </li>
            <% } %>
        </ul>
    </div>
</nav>