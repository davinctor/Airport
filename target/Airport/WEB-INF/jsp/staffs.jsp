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
    <c:if test="${not empty staffDeleteSuccess}">
        <div class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Успех!</strong> ${staffDeleteSuccess}
        </div>
    </c:if>
    <c:if test="${not empty staffNotFound}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Внимание!</strong> ${staffNotFound}
        </div>
    </c:if>
    <div class="page-header row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1>Персонал аэропорта</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-offset-1  col-md-10">
            <table class="table table-bordered table-hover table-centered text-center">
                <tr>
                    <th>ФИО</th>
                    <th>Отдел</th>
                    <th>Адрес</th>
                    <th>Номер телефона</th>
                    <th>Действие</th>
                </tr>
                <c:forEach var="staff" items="${staffs}">
                    <tr>
                        <td>
                                ${staff.surname} ${staff.firstname} ${staff.patronymic}
                        </td>
                        <td>
                            <c:set value="${staff.department}" var="department"/>
                            <a href="${homeUrl}/department/${department.id}">
                                    ${staff.department.name}
                            </a>
                        </td>
                        <td>
                                ${staff.address}
                        </td>
                        <td>
                            <c:set var="count" value="0"/>
                            <c:forEach var="phone" items="${staff.phones}">
                                ${count > 0 ? '<br/>': '' }
                                ${phone.phoneNumber}
                                <c:set var="count" value="${count+1}"/>
                            </c:forEach>
                        </td>
                        <td>
                            <div class="btn-group">
                                <a class="btn btn-danger" href="${homeUrl}/staff/${staff.id}" role="button">Просмотр</a>
                                <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li>
                                        <a href="${homeUrl}/staff/edit/${staff.id}" role="button">Изменить</a>
                                        <a href="" resource="${homeUrl}/staff/delete/${staff.id}" role="button"
                                           data-toggle="modal" data-target="#deleteModal">Удалить</a>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="modal fade text-left" id="deleteModal" role="dialog">
        <div class="modal-dialog modal-dialog-center">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">Удаление данных о сотруднике</h4>
                </div>
                <div class="modal-body">
                    Вы действительно хотите удалить данные от сотруднике?
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
