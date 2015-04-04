<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <jsp:include page="fragments/staticFiles.jsp"/>
    <c:set var="title"
           value=" ${staff.id == 0 ? 'Новый сотрудник' : staff.surname.concat(' ').concat(staff.firstname)}"/>
    <title>${title}</title>
</head>
<body>
<jsp:include page="fragments/navbar.jsp"/>
<div class="page-body container-fluid text-center">

    <div class="page-header row">
        <div class="col-lg-8 col-lg-offset-2 col-md-offset-2 col-md-8 col-sm-12">
            <h2>
                ${title}
            </h2>
        </div>
    </div>

    <div class="row">
        <div class="col-md-offset-3 col-md-6 col-lg-6 col-sm-offset-2 col-sm-8 col-xs-12">
            <form:form action="/staff/${staff.id == 0 ? 'new' : 'edit/'.concat(staff.id)}" method="post"
                       modelAttribute="staff"
                       cssClass="form-horizontal">

                <c:set var="surnameError"><form:errors path="surname"/></c:set>
                <div class="form-group ${(not empty surnameError) ? 'has-error' : ''}">
                    <label for="surname" class="col-md-3 control-label">Фамилия</label>

                    <div class="col-md-7">
                        <form:input path="surname" type="text" cssClass="form-control" id="surname"
                                    placeholder="Введите фамилию сотрудника"/>
                    </div>
                    <span class="col-md-2">
                            ${not empty surnameError ? surnameError : ''}
                    </span>
                </div>

                <c:set var="firstnameError"><form:errors path="firstname"/></c:set>
                <div class="form-group ${(not empty firstnameError) ? 'has-error' : ''}">
                    <label for="firstname" class="col-md-3 control-label">Имя</label>

                    <div class="col-md-7">
                        <form:input path="firstname" type="text" cssClass="form-control" id="firstname"
                                    placeholder="Введите имя сотрудника"/>
                    </div>
                    <span class="col-md-2">
                            ${not empty firstnameError ? firstnameError : ''}
                    </span>
                </div>

                <c:set var="patronymicError"><form:errors path="patronymic"/></c:set>
                <div class="form-group ${(not empty patronymicError) ? 'has-error' : ''}">
                    <label for="patronymic" class="col-md-3 control-label">Отчество</label>

                    <div class="col-md-7">
                        <form:input path="patronymic" type="text" cssClass="form-control" id="patronymic"
                                    placeholder="Введите отчество сотрудника" readonly="${staff.id != 0}"/>
                    </div>
                    <span class="col-md-2">
                            ${not empty patronymicError ? patronymicError : ''}
                    </span>
                </div>

                <c:set var="addressError"><form:errors path="address"/></c:set>
                <div class="form-group ${(not empty addressError) ? 'has-error' : ''}">
                    <label for="address" class="col-md-3 control-label">Адрес</label>

                    <div class="col-md-7">
                        <form:textarea path="address" type="text" cssClass="form-control" id="address"
                                       placeholder="Введите адрес сотрудника"/>
                    </div>
                    <span class="col-md-2">
                            ${not empty addressError ? addressError : ''}
                    </span>
                </div>

                <div class="form-group ${(not empty phonesError) ? 'has-error' : ''}">
                    <label for="phones" class="col-md-3 control-label">Телефоны</label>
                    <div class="col-md-7">
                        <textarea name="phone-numbers" class="form-control" id="phones"
                                  placeholder="Введите номера телефонов сотрудника. Каждый номер с новой строки">${phones}</textarea>
                    </div>
                    <span class="col-md-2">
                            ${not empty phonesError ? phonesError : ''}
                    </span>
                </div>

                <c:set var="emptyDepartments" value="${fn:length(departments) == 0}"/>
                <div class="form-group ${not empty departmentError ? 'has-error' : ''}">
                    <label for="department" class="col-md-3 control-label">Отдел</label>
                    <c:if test="${staff.id != 0}">
                        <form:hidden path="department"/>
                    </c:if>
                    <div id="department">
                        <c:choose>
                            <c:when test="${not emptyDepartments}">
                                <div class="col-md-7">
                                    <select class="form-control" name="department-id">
                                        <c:forEach items="${departments}" var="department">
                                            <option value="${department.id}" ${staff.department.id == department.id ? 'selected' : '' }>
                                                    ${department.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-md-7">
                                    <div class="well text-danger">
                                        Необходимо создать хотя-бы один отдел. Список отделов пуст.
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <span class="col-md-2">
                            ${not empty departmentError ? departmentError: ''}
                    </span>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-1 col-md-10">
                        <button class="btn btn-lg btn-success btn-block" type="submit">
                                ${staff.id == 0 ? 'Создать' : 'Обновить'} <span class="fa fa-suitcase"></span>
                        </button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
<!-- container-fluid -->
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>