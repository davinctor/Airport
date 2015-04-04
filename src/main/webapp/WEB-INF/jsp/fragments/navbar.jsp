<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:url value="${pageContext.request.contextPath}" var="homeUrl"/>
<sec:authorize var="isAdmin" access="hasRole('ROLE_ADMIN')"/>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <!-- Responsive display for liw-width devices -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#navigation-bar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"><span class="fa fa-plane"></span> AirMan</a>
        </div>

        <!-- Collect the nav links, forms -->
        <div class="collapse navbar-collapse" id="navigation-bar">
            <ul class="nav navbar-nav">
                <spring:url value="${homeUrl}/reservation" var="itemMenuUrl"/>
                <li class="dropdown
          ${fn:contains(pageContext.request.requestURL,itemMenuUrl)?'active':''}">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
                        <span class="fa fa-ticket"></span> Бронирование <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="${itemMenuUrl}/find">Бронирование места</a></li>
                        <li class="divider"></li>
                        <li><a href="${itemMenuUrl}s">Список брони</a></li>
                    </ul>
                </li>
                <spring:url value="${homeUrl}/flight" var="itemMenuUrl"/>
                <li class="
          ${isAdmin?'dropdown ':''}
          ${fn:contains(pageContext.request.requestURL,itemMenuUrl) ? 'active':''}">
                    <c:if test="${isAdmin}">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
                            <span class="fa fa-plane"></span> Рейсы <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="${itemMenuUrl}/new">Создание нового рейса</a></li>
                            <li class="divider"></li>
                            <li><a href="${itemMenuUrl}s">Управление рейсами</a></li>
                        </ul>
                    </c:if>
                    <c:if test="${!isAdmin}">
                        <a href="${itemMenuUrl}" role="button">
                            <span class="fa fa-plane"></span> Рейсы
                        </a>
                    </c:if>
                </li>
                <spring:url value="${homeUrl}/registration" var="itemMenuUrl"/>
                <li class="dropdown
          ${fn:contains(pageContext.request.requestURL,itemMenuUrl)?' active':''}">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
                        <span class="fa fa-check"></span> Регистрация <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="${itemMenuUrl}/new">Регистрация пассажира</a></li>
                        <li class="divider"></li>
                        <li><a href="${itemMenuUrl}s">Список регистраций</a></li>
                    </ul>
                </li>
                <spring:url value="${homeUrl}/user" var="itemMenuUrl"/>
                <c:if test="${isAdmin}">
                    <li class="
            ${fn:contains(pageContext.request.requestURL,itemMenuUrl)?'active':''}">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
                            <span class="fa fa-users"></span> Пользователи
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="${itemMenuUrl}/new">Создание нового пользователя</a></li>
                            <li class="divider"></li>
                            <li><a href="${itemMenuUrl}s">Список пользователей</a></li>
                        </ul>
                    </li>
                </c:if>
                <spring:url value="${homeUrl}/staff" var="itemMenuUrl"/>
                <c:if test="${isAdmin}">
                    <li class="
            ${fn:contains(pageContext.request.requestURL,itemMenuUrl)?'active':''}">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
                            <span class="fa fa-suitcase"></span> Персонал
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="${itemMenuUrl}/new">Новый сотрудник</a></li>
                            <li class="divider"></li>
                            <li><a href="${itemMenuUrl}s">Персонал</a></li>
                        </ul>
                    </li>
                </c:if>
                <spring:url value="${homeUrl}/department" var="itemMenuUrl"/>
                <c:if test="${isAdmin}">
                    <li class="
            ${fn:contains(pageContext.request.requestURL,itemMenuUrl)?'active':''}">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
                            <span class="fa fa-building"></span> Отделы
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="${itemMenuUrl}/new">Новый отдел</a></li>
                            <li class="divider"></li>
                            <li><a href="${itemMenuUrl}s">Список отделов</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
            <form class="navbar-form navbar-right" action="j_spring_security_logout" method="post">
                <div class="btn-group">
                    <a class="btn btn-primary" href="${homeUrl}/user/${curUser.id}" role="button">
                        <c:set value="${curUser.staff}" var="curStaff"/>
                        ${curStaff.surname} ${curStaff.firstname}
                    </a>
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-sign-out"></i>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
