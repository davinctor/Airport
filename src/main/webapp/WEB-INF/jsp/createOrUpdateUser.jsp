<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <jsp:include page="fragments/staticFiles.jsp"/>
    <title>${user.id == 0 ? 'Новый пользователь' : user.login }</title>
</head>
<body>
<jsp:include page="fragments/navbar.jsp"/>
<div class="page-body container-fluid text-center">

    <div class="page-header row">
        <div class="col-lg-8 col-lg-offset-2 col-sm-12">
            <h2>
                ${user.id == 0 ? 'Новый пользователь' : user.login }
            </h2>
        </div>
    </div>

    <div class="row">
        <div class="col-md-offset-3 col-md-6 col-lg-offset-3 col-lg-6 col-sm-offset-2 col-sm-8 col-xs-12">
            <form:form action="/user/${user.id == 0 ? 'new' : 'edit/'.concat(user.id)}" method="post"
                       modelAttribute="user" cssClass="form-horizontal">

                <c:set var="loginError"><form:errors path="login"/></c:set>
                <div class="form-group ${(not empty loginError) or (not empty usernameExists) ? 'has-error' : ''}">
                    <label for="login" class="col-md-3 control-label">Имя пользователя</label>

                    <div class="col-md-7">
                        <form:input path="login" type="text" cssClass="form-control" id="login"
                                    placeholder="Введите имя пользователя" readonly="${user.id != 0}"/>
                    </div>
          <span class="col-md-2">
            ${not empty loginError ? loginError : ''} ${not empty usernameExists ? usernameExists : ''}
          </span>
                </div>

                <c:set var="passError"><form:errors path="password"/></c:set>
                <div class="form-group ${not empty passError ? 'has-error' : ''}">
                    <label for="pass" class="col-md-3 control-label">Пароль</label>

                    <div class="col-md-7">
                        <form:input path="password" type="password" cssClass="form-control" id="pass"
                                    placeholder="Введите пароль"/>
                    </div>
                    <span class="col-md-2">${not empty passError ? passError: ''}</span>
                </div>

                <div class="form-group ${not empty rePassError ? 'has-error' : ''}">
                    <label for="pass" class="col-md-3 control-label">Повторите пароль</label>

                    <div class="col-md-7">
                        <input name="re-password" type="password" class="form-control" id="pass"
                               placeholder="Введите пароль"/>
                    </div>
                    <span class="col-md-2">${not empty rePassError ? rePassError: ''}</span>
                </div>

                <c:set var="roleError"><form:errors path="roleOfUser"/></c:set>
                <div class="form-group ${not empty roleError ? 'has-error' : ''}">
                    <label for="role" class="col-md-3 control-label">Права доступа</label>

                    <div class="col-md-7">
                        <form:select path="roleOfUser" cssClass="form-control" id="role">
                            <form:option value="ROLE_ADMIN">
                                Администратор
                            </form:option>
                            <form:option value="ROLE_USER">
                                Пользователь
                            </form:option>
                        </form:select>
                    </div>
                    <span class="col-md-2">${not empty roleError ? roleError: ''}</span>
                </div>

                <c:set var="emptyDepartments" value="${fn:length(departments) == 0}"/>
                <div class="form-group ${not empty staffError ? 'has-error' : ''}">
                    <c:if test="${user.id != 0}">
                        <form:hidden path="staff"/>
                    </c:if>
                    <label for="staff" class="col-md-3 control-label">Профиль сотрудника</label>

                    <div class="staff-profile" id="staff">
                        <c:choose>
                            <c:when test="${not emptyDepartments}">
                                <c:set var="selectShow" value="1"/>
                                <div class="col-md-3">
                                    <select class="form-control" id="departments-select">
                                        <c:set var="count" value="1"/>
                                        <c:forEach items="${departments}" var="department">
                                            <option value="department-${department.id}"
                                                    <c:if test="${user.staff.department.id == department.id}">
                                                        selected
                                                        <c:set var="selectShow" value="${count}"/>
                                                    </c:if>>
                                                    ${department.name}
                                            </option>
                                            <c:set var="count" value="${count + 1}"/>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-4">
                                    <input class="hide" name="staff-id"
                                           value="${staff.id == 0 ? -1 : user.staff.id }"/>
                                    <c:set var="count" value="1"/>
                                    <c:forEach items="${departments}" var="department">
                                        <select class="${count == selectShow ? '' : 'hide'} form-control department"
                                                id="department-${department.id}">
                                            <option value="-1">Выбрать</option>
                                            <c:forEach items="${department.staffs}" var="staff">
                                                <option value="${staff.id}" ${staff.id == user.staff.id ? 'selected' : ''}>
                                                        ${staff.surname} ${staff.firstname}
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <c:set var="count" value="${count+1}"/>
                                    </c:forEach>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-md-7">
                                    <div class="well text-danger">
                                        У всех сотрудников есть профиль пользователя.
                                        Вы не можете создать нового пользователя.
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
            <span class="col-md-2">
                    ${(not empty staffError) and (empty emptyDepartments) ? staffError: ''}
            </span>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-1 col-md-10">
                        <button class="btn btn-lg btn-success btn-block" type="submit">
                                ${user.id == 0 ? "Создать" : "Обновить"} <span class="fa fa-user"></span>
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