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
    <title>Новый отдел</title>
</head>
<body>
<jsp:include page="fragments/navbar.jsp"/>
<div class="page-body container-fluid text-center">

    <div class="page-header row">
        <div class="col-lg-8 col-lg-offset-2">
            <h2>
                ${department.id == 0 ? "Новый отдел" : "Обновить данные"}
            </h2>
        </div>
    </div>

    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <form:form action="${requestScope['javax.servlet.forward.request_uri']}" method="post"
                       modelAttribute="department"
                       cssClass="form-horizontal">

                <c:set var="nameError"><form:errors path="name"/></c:set>
                <div class="form-group ${(not empty nameError) ? 'has-error' : ''}">
                    <label for="name" class="col-md-3 control-label">
                            ${department.id == 0 ? "Название отдела" : department.name}
                    </label>

                    <div class="col-md-7">
                        <form:input path="name" type="text" cssClass="form-control" id="name"
                                    placeholder="Введите название отдела"/>
                    </div>
          <span class="col-md-2">
                  ${not empty nameError ? nameError : ''}
          </span>
                </div>

                <c:set var="phoneNumError"><form:errors path="phoneNum"/></c:set>
                <div class="form-group ${(not empty phoneNumError) ? 'has-error' : ''}">
                    <label for="phone-number" class="col-md-3 control-label">Номер телефона</label>

                    <div class="col-md-7">
                        <form:input path="phoneNum" type="text" cssClass="form-control" id="phone-number"
                                    placeholder="Введите номер телефона"/>
                    </div>
          <span class="col-md-2">
                  ${not empty phoneNumError ? phoneNumError : ''}
          </span>
                </div>

                <div class="form-group ${(not empty scheduleFromError) ? 'has-error' : ''}">
                    <label for="time-from" class="col-md-4 control-label">Время начала работы</label>

                    <div class="col-md-6">
                        <div class="input-group datetimepicker">
                            <input id="time-from" name="scheduleFromTime" class="form-control"
                                   placeholder="Время начала работы" type="text"
                                   value="${scheduleFrom}">
            	<span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
              	</span>
                        </div>
                    </div>
          <span class="col-md-2">
                  ${not empty scheduleFromError ? scheduleFromError : ''}
          </span>
                </div>

                <div class="form-group ${(not empty scheduleToError) ? 'has-error' : ''}">
                    <label for="time-to" class="col-md-4 control-label">Время окончания работы</label>

                    <div class="col-md-6">
                        <div class="input-group datetimepicker">
                            <input id="time-to" name="scheduleToTime" class="form-control"
                                   placeholder="Время окончания работы" type="text"
                                   value="${scheduleTo}">
            	<span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
              	</span>
                        </div>
                    </div>
          <span class="col-md-2">
                  ${not empty scheduleToError ? scheduleToError : ''}
          </span>
                </div>

                <div class="form-group ${(not empty breakFromError) ? 'has-error' : ''}">
                    <label for="break-from" class="col-md-4 control-label">Время начала перерыва</label>

                    <div class="col-md-6">
                        <div class="input-group datetimepicker">
                            <input id="break-from" name="breakFromTime" class="form-control"
                                   placeholder="Время начала перерыва" type="text"
                                   value="${breakFrom}">
            	<span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
              	</span>
                        </div>
                    </div>
          <span class="col-md-2">
                  ${not empty breakFromError ? breakFromError : ''}
          </span>
                </div>

                <div class="form-group ${(not empty breakToError) ? 'has-error' : ''}">
                    <label for="break-to" class="col-md-4 control-label">Время окончания перерыва</label>

                    <div class="col-md-6">
                        <div class="input-group datetimepicker">
                            <input id="break-to" name="breakToTime" class="form-control"
                                   placeholder="Время окончания перерыва" type="text"
                                   value="${breakTo}">
            	<span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
              	</span>
                        </div>
                    </div>
          <span class="col-md-2">
                  ${not empty breakToError ? breakToError : ''}
          </span>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-1 col-md-10">
                        <button class="btn btn-lg btn-success btn-block" type="submit">
                                ${department.id == 0 ? "Создать" : "Обновить" } <span class="fa fa-building"></span>
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