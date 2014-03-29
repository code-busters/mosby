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
            <fmt:message key="sideNavnar.myAccount"/>
        </li>
        <li>
            <a href="<c:url value="/contactInfo"/>"><fmt:message key="sideNavnar.contactInfo"/></a>
        </li>
        <li>
            <a href="<c:url value="/contactInfo"/>"><fmt:message key="sideNavnar.tickets"/></a>
        </li>
        <li>
            <a href="<c:url value="/changePassword"/>"><fmt:message key="sideNavnar.changePassword"/></a>
        </li>
        <li>
            <a href="<c:url value="/myEvents"/>"><fmt:message key="sideNavnar.myEvents"/></a>
        </li>
        <li>
            <a href="<c:url value="/myOrganizers"/>"><fmt:message key="sideNavnar.myOrganizers"/></a>
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