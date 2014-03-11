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
                    <li>
                        <a href="#">Business</a>
                    </li>
                    <li>
                        <a href="#">Music</a>
                    </li>
                    <li>
                        <a href="#">Food &amp; Drink</a>
                    </li>
                    <li>
                        <a href="#">Film &amp; Media</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">All</a>
                    </li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <% if (request.getSession().getAttribute("baseUserInfo") != null) { %>
            <li>
                <div class="flow-img nav-user-img" style="background-image: url(media/images/users/default.jpg)"></div>
            </li>
            <li class="divider"></li>
            <li>
                <a href="<c:url value="/index?logout=${baseUserInfo.id}"/>">Log Out</a>
            </li>
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