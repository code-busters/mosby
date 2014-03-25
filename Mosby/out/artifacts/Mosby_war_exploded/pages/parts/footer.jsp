<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="main.java.com.mosby.i18n.text"/>
<footer class="bottom-menu">
    <div class="col-md-2 col-sm-2 col-xs-12 col-md-offset-1 col-sm-offset-1 brand">
        <a class="navbar-brand" href="#"></a>
    </div>

    <div class="col-md-7 col-sm-6">
        <ul class="bottom-links">
            <li>
                <a href="#fakelink">About Us</a>
            </li>
            <li>
                <a href="#fakelink">Store</a>
            </li>
            <li>
                <a href="#fakelink">Privacy</a>
            </li>
            <li>
                <a href="#fakelink">Follow Us</a>
            </li>
            <li>
                <a href="#fakelink">Support</a>
            </li>
        </ul>
    </div>

    <div class="col-md-2 col-sm-3">
        <ul class="bottom-icons">
            <%--<li class="lang">--%>
            <%--<c:choose>--%>
                <%--<c:when test="${language} == 'uk'">--%>
                   <%--UA|<a href="<%= request.getContextPath()%>?language='en'}">EN</a>--%>
                <%--</c:when>--%>
                <%--<c:when test="${language}== 'en'">--%>
                    <%--<a href="<%= request.getContextPath()%>?language='uk'">UA</a>|EN--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <%--No ${language == 'uk' ? 'EN' : 'UA'}--%>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>
            <%--</li>--%>
                <li class="lang">
                    <a href="<%= request.getContextPath()%>?language=${language == 'uk' ? 'en' : 'uk'}">
                    ${language == 'uk' ? 'EN' : 'UA'}
                </a>
                </li>
            <li>
                <a href="#fakelink" class="fui-googleplus"></a>
            </li>
            <li>
                <a href="#fakelink" class="fui-facebook"></a>
            </li>
            <li>
                <a href="#fakelink" class="fui-twitter"></a>
            </li>
        </ul>
    </div>
</footer>