<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<spring:url value="${pageContext.request.contextPath}" var="homeUrl"/>
<head>
    <jsp:include page="fragments/staticFiles.jsp"/>
    <title>Пользователи</title>
</head>

<body>
<jsp:include page="fragments/navbar.jsp"/>
<div class="page-body container-fluid text-center">
    <c:if test="${not empty deleteUserSuccess}">
        <div class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <strong>Успех!</strong> ${deleteUserSuccess}
        </div>
    </c:if>
    <c:if test="${not empty userNotFound}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <strong>Внимание!</strong> ${userNotFound}
        </div>
    </c:if>
    <div class="page-header row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1>Список пользователей</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <form action="/user/search" method="get">
                <div class="form-inline search-panel">
                    <div class="form-group">
                        <input type="text" class="form-control" name="username"
                               placeholder="Введите имя пользователя..."
                               value="${username}">
                    </div>
                    <div class="form-group">
                        <select class="form-control" name="role">
                            <option value=1
                            ${(role == null || role == 1) ? 'selected' : ''}>
                                Не выбрано
                            </option>
                            <option value=2
                            ${(role == 2) ? 'selected' : ''}>
                                Администратор
                            </option>
                            <option value=3
                            ${(role == 3) ? 'selected' : ''}>
                                Касир
                            </option>
                        </select>
                    </div>
                    <span class="form-group">
                        <button class="btn btn-default" type="submit">
                            <span class="fa fa-search"></span>
                        </button>
                    </span>
                </div>
            </form>
        </div>
        <div class="col-md-offset-3 col-md-6">
            <c:choose>
                <c:when test="${empty searchError}">
                    <table class="table table-bordered table-hover table-centered text-center">
                    <tr>
                        <th>Имя пользователя</th>
                        <th>Уровень доступа</th>
                        <th>Профиль сотрудника</th>
                        <th>Действие</th>
                    </tr>
                        <c:forEach var="user" items="${users}">
                            <c:set value="${user.staff}" var="staff"/>
                            <tr>
                                <td><c:out value="${user.login}"/></td>
                                <td>
                                        ${user.roleOfUser == 'ROLE_ADMIN' ? 'Администратор' : 'Пользователь'}
                                </td>
                                <td>
                                    <a class="btn btn-info" href="/staff/${staff.id}">
                                            ${staff.surname} ${staff.firstname}
                                    </a>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <a class="btn btn-danger" href="${homeUrl}/user/${user.id}" role="button">Просмотр</a>
                                        <button type="button" class="btn btn-danger dropdown-toggle"
                                                data-toggle="dropdown">
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li>
                                                <a href="${homeUrl}/user/edit/${user.id}" role="button">Изменить</a>
                                                <a href="" resource="${homeUrl}/user/delete/${user.id}" role="button"
                                                   data-toggle="modal" data-target="#deleteModal">Удалить</a>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <h4>${searchError}</h4>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="modal fade text-left" id="deleteModal" role="dialog">
        <div class="modal-dialog modal-dialog-center">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">Удаление пользователя</h4>
                </div>
                <div class="modal-body">
                    Вы действительно хотите удалить пользователя?
                </div>
                <div class="modal-footer">
                    <a href="" class="btn btn-primary btn-yes">Да</a>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Нет</button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
