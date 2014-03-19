<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li class="sidebar-brand">
            YOUR ACCOUNT
        </li>
        <li>
            <a href="<c:url value="/contactInfo"/>">Contact Info</a>
        </li>
        <li>
            <a href="<c:url value="/contactInfo"/>">Tickets</a>
        </li>
        <li>
            <a href="<c:url value="/changePassword"/>">Change Password</a>
        </li>
        <li>
            <a href="<c:url value="/myEvents"/>">My Events</a>
        </li>
        <li>
            <a href="<c:url value="/myOrganizers"/>">Organizers</a>
        </li>
        <li>
            <a href="#">About</a>
        </li>
        <li>
            <a href="#">Services</a>
        </li>
        <li>
            <a href="#">Contact</a>
        </li>
    </ul>
</div>