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
            <fmt:message key="sideNavbar.myAccount"/>
        </li>
        <li>
            <a href="<c:url value="/contactInfo"/>"><fmt:message key="sideNavbar.contactInfo"/></a>
        </li>
        <li>
            <a href="<c:url value="/myTickets"/>"><fmt:message key="sideNavbar.tickets"/></a>
        </li>
        <c:if test="${userType == 'common'}">
        <li>
            <a href="<c:url value="/changePassword"/>"><fmt:message key="sideNavbar.changePassword"/></a>
        </li>
        </c:if>
        <li>
            <a href="<c:url value="/myEvents"/>"><fmt:message key="sideNavbar.myEvents"/></a>
        </li>
        <li>
            <a href="<c:url value="/myOrganizers"/>"><fmt:message key="sideNavbar.myOrganizers"/></a>
        </li>
        <li>
            <a href="<c:url value="/apiAccess"/>"><fmt:message key="sideNavbar.apiAccess"/></a>
        </li>
    </ul>
</div>