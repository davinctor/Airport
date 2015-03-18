<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="fragments/staticFiles.jsp"/>
    <title>Пользователь ${user.login}</title>
</head>
<body>
<jsp:include page="fragments/navbar.jsp"/>
<div class="page-body container-fluid text-center">
    <c:if test="${not empty restrictDeleteStaff}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Внимание!</strong> ${restrictDeleteStaff}
        </div>
    </c:if>
    <div class="page-header row">
        <div class="col-lg-8 col-lg-offset-2">
            <h2>
                ${user.login}
                <small>
                    <a href="/user/edit/${user.id}" class="btn btn-default">
                        <span class="fa fa-pencil"
                              data-original-title="Редактировать данные" data-toggle="tooltip">
                        </span>
                    </a>
                    <a href="" resource="/user/delete/${user.id}" class="btn btn-default"
                       role="button" data-toggle="modal" data-target="#deleteModal">
                        <span class="fa fa-eraser"
                              data-original-title="Удалить пользователя" data-toggle="tooltip">
                        </span>
                    </a>
                </small>
            </h2>
        </div>
    </div>
    <div class="col-md-offset-4 col-md-4 row">
        <ul class="list-group">
            <dl class="dl-horizontal">
                <li class="list-group-item">
                    <dt>Логин</dt>
                    <dd>${user.login}</dd>
                </li>
                <li class="list-group-item">
                    <dt>Уровень доступа</dt>
                    <dd>
                        ${user.roleOfUser == 'ROLE_ADMIN' ? 'Администратор' : 'Пользователь'}
                    </dd>
                </li>
                <li class="list-group-item">
                    <dt>Профиль сотрудника</dt>
                    <dd>
                        <c:set value="${user.staff}" var="staff"/>
                        <a class="btn btn-info btn-sm" href="/staff/${staff.id}">
                            ${staff.surname} ${staff.firstname}
                        </a>
                    </dd>
                </li>
            </dl>
        </ul>
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
