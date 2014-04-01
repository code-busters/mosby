<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="main.java.com.mosby.i18n.text"/>
<div id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li class="sidebar-brand">
            <fmt:message key="sideNavnar.manageEvent"/>
        </li>
        <li>
            <a href="<c:url value="/editEvent?eventId=${event.id}"/>"><fmt:message key="sideNavnar.editEvent"/></a>
        </li>
        <li>
            <a href="<c:url value="/editTicketsPromoCodes?eventId=${event.id}"/>"><fmt:message key="sideNavnar.editTicketsPromoCodes"/></a>
        </li>
        <li>
            <a href="<c:url value="/registeredTable?eventId=${event.id}"/>"><fmt:message key="sideNavnar.registeredTable"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="sideNavnar.events"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="sideNavnar.about"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="sideNavnar.services"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="sideNavnar.contact"/></a>
        </li>
    </ul>
</div>