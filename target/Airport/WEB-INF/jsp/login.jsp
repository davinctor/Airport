<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <jsp:include page="fragments/staticFiles.jsp"/>
    <title>Вход</title>
</head>

<body>
<div id="wrapper" class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default panel-login">
                <div class="panel-heading">
                    <h3 class="panel-title">Авторизация</h3>
                </div>
                <div class="panel-body">
                    <spring:url var="authUrl" value="j_spring_security_check"/>
                    <c:choose>
                        <c:when test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                                    ${error}
                            </div>
                        </c:when>
                        <c:when test="${not empty logout}">
                            <div class="alert alert-success" role="alert">
                                    ${logout}
                            </div>
                        </c:when>
                    </c:choose>
                    <form:form name="loginForm" method="post" action="${authUrl}">
                        <fieldset>
                            <c:choose>
                            <c:when test="${not empty error}">
                            <div class="form-group has-error">
                                </c:when>
                                <c:otherwise>
                                <div class="form-group">
                                    </c:otherwise>
                                    </c:choose>

                                    <div class="input-group">
                                        <input type="text" class="form-control" id="inputUsername"
                                               placeholder="Имя пользователя" name="j_username"/>
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    </div>
                                </div>

                                <c:choose>
                                <c:when test="${not empty error}">
                                <div class="form-group has-error">
                                    </c:when>
                                    <c:otherwise>
                                    <div class="form-group">
                                        </c:otherwise>
                                        </c:choose>
                                        <div class="input-group">
                                            <input type="password" class="form-control" id="inputPassword"
                                                   placeholder="Пароль" name="j_password"/>
                                            <span class="input-group-addon"><i class="fa fa-shield"></i></span>
                                        </div>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input name="j_spring_security_remember_me"
                                                   type="checkbox"> Запомнить меня
                                            </input>
                                        </label>
                                    </div>
                                    <button type="submit" class="btn btn-lg btn-success btn-block" name="submit">
                                        <span class="fa fa-sign-in"></span> Войти
                                    </button>
                        </fieldset>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>