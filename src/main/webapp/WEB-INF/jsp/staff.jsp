<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<c:set value="${staff.surname} ${staff.firstname}" var="firstLastName"/>
<head>
    <jsp:include page="fragments/staticFiles.jsp"/>
    <title>${firstLastName}</title>
</head>
<body>
<jsp:include page="fragments/navbar.jsp"/>
<div class="container-fluid text-center">
    <div class="page-header row">
        <div class="col-lg-8 col-lg-offset-2">
            <h2>
                ${firstLastName}
                <small>
                    <a href="/staff/edit/${staff.id}" class="btn btn-default"
                       data-original-title="Редактировать профиль" data-toggle="tooltip">
                        <span class="fa fa-pencil"></span>
                    </a>
                    <a href="" resource="/staff/delete/${staff.id}" class="btn btn-default"
                       role="button" data-toggle="modal" data-target="#deleteModal">
            <span class="fa fa-eraser"
                  data-original-title="Удалить профиль" data-toggle="tooltip"></span>
                    </a>
                </small>
            </h2>
        </div>
    </div>
    <div class="col-md-offset-4 col-md-4 row">
        <ul class="list-group">
            <dl class="dl-horizontal">
                <li class="list-group-item">
                    <dt>ФИО</dt>
                    <dd>${staff.surname} ${staff.firstname} ${staff.patronymic}</dd>
                </li>
                <li class="list-group-item">
                    <dt>Отдел</dt>
                    <dd>
                        <c:set value="${staff.department}" var="department"/>
                        <a href="/department/${department.id}">${department.name}</a>
                    </dd>
                </li>
                <li class="list-group-item">
                    <dt>Адрес</dt>
                    <dd>${staff.address}</dd>
                </li>
                <li class="list-group-item">
                    <dt>Номер телефона</dt>
                    <dd>
                        <c:set var="count" value="0"/>
                        <c:forEach var="phone" items="${staff.phones}">
                            ${count > 0 ? '<br/>': '' }
                            ${phone.phoneNumber}
                            <c:set var="count" value="${count+1}"/>
                        </c:forEach>
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
                    <h4 class="modal-title">Удаление профиля</h4>
                </div>
                <div class="modal-body">
                    Вы действительно хотите удалить профиль сотрудника?
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
