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
    <title>Список отделов</title>
</head>

<body>
<jsp:include page="fragments/navbar.jsp"/>
<div class="page-body container-fluid text-center">
    <c:if test="${not empty deleteDepartmentSuccess}">
        <div class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <strong>Успех!</strong> ${deleteDepartmentSuccess}
        </div>
    </c:if>
    <c:if test="${not empty departmentNotFound}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <strong>Внимание!</strong> ${departmentNotFound}
        </div>
    </c:if>
    <div class="page-header row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1>Список отделов</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-offset-2  col-md-8">
            <table class="table table-bordered table-hover table-centered text-center">
                <tr>
                    <th>Название отдела</th>
                    <th>Режим работы</th>
                    <th>Перерыв</th>
                    <th>Номер телефона</th>
                    <th>Действие</th>
                </tr>
                <c:forEach var="department" items="${departments}">
                    <tr>
                        <td><c:out value="${department.name}"/></td>
                        <td>
                            <span class="fa fa-clock-o"></span>
                                ${department.scheduleFrom} : ${department.scheduleTo}
                        </td>
                        <td>
                            <span class="fa fa-coffee"></span>
                                ${department.breakFrom} : ${department.breakTo}
                        </td>
                        <td>
                                ${department.phoneNum}
                        </td>
                        <td>
                            <div class="btn-group">
                                <a class="btn btn-danger" href="${homeUrl}/department/${department.id}" role="button">Просмотр</a>
                                <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li>
                                        <a href="${homeUrl}/department/edit/${department.id}" role="button">Изменить</a>
                                        <a href=""
                                           resource="${homeUrl}/department/delete/${department.id}?departmentName=${department.name}"
                                           role="button"
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
                    <h4 class="modal-title">Удаление отдела</h4>
                </div>
                <div class="modal-body">
                    Вы действительно хотите удалить отдел из базы данных?
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
