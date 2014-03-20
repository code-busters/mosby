<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li class="sidebar-brand">
            MANAGE EVENT
        </li>
        <li>
            <a href="<c:url value="/editEvent?eventId=${event.id}"/>">Edit Event</a>
        </li>
        <li>
            <a href="<c:url value="/editTicketsPromoCodes?eventId=${event.id}"/>">Edit Tickets &amp; Promo-codes</a>
        </li>
        <li>
            <a href="<c:url value="/registeredTable?eventId=${event.id}"/>">Registered Table</a>
        </li>
        <li>
            <a href="#">Events</a>
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