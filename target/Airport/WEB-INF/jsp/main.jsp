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
    <title>Главная</title>
</head>
<body>
<jsp:include page="fragments/navbar.jsp"/>
<c:set value="0" var="count"/>
<div class="page-body container-fluid text-center">
    <div class="page-header row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1>Главная</h1>
        </div>
    </div>
    <c:set value="4" var="columnCount"/>
    <sec:authorize var="isAdmin" access="hasRole('ROLE_ADMIN')"/>
    <c:forEach items="${listServices}" var="service">
        <c:if test="${(service.isForAdmin && isAdmin) ||
    !service.isForAdmin}">
            ${count % columnCount == 0 ? '<div class="row">' : '' }
            <div class="
        ${count % columnCount == 0 ? 'col-md-offset-2 ' : '' }
        col-md-2">
                <div class="panel panel-default">
                    <a class="panel-body"
                       href="${pageContext.request.contextPath}${service.url}">
                        <span class="fa ${service.icon} fa-5x"></span>
                        <h5>${service.nameService}</h5>
                    </a>
                </div>
            </div>
            <c:set var="count" value="${count+1}"/>
            ${count % columnCount == 0 ? '</div>' : '' }
        </c:if>
    </c:forEach>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>