<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="main.java.com.mosby.i18n.text"/>
<footer class="bottom-menu">
    <div class="col-md-1 col-sm-2 col-xs-12 col-md-offset-1 col-sm-offset-1 brand">
        <a class="navbar-brand" href="<c:url value="/index"/>"></a>
    </div>

    <div class="col-md-7 col-sm-6">
        <ul class="bottom-links">
            <li class="copy-right">
                &#64; Code busters 2014
            </li>
            <li>
                <a href="<c:url value="/aboutUs"/>"><fmt:message key="footer.aboutUs"/></a>
            </li>
        </ul>
    </div>

    <div class="col-md-2 col-sm-3">
        <ul class="bottom-icons">
            <li class="lang">
                <a href="<%= request.getContextPath()%>?language=${language == 'uk' ? 'en' : 'uk'}">
                    ${language == 'uk' ? 'EN' : 'UA'}
                </a>
            </li>
            <li>
                <a href="https://plus.google.com/100002641608230461293" target="_blank" class="fui-googleplus"></a>
            </li>
            <li>
                <a href="mailto:mosby.events@gmail.com" target="_blank" class="fui-mail"></a>
            </li>
        </ul>
    </div>
</footer>