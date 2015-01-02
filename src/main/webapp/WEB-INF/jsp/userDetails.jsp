<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<jsp:include page="fragments/staticFiles.jsp"/>

<body>
<div id="wrapper" class="container">
    <div class="row">
        <div class="col-md-12">
            <h2>User</h2>

            <table class="table table-bordered" style="width: 600px;">
                <tr>
                    <th>Login Name</th>
                    <td><b><c:out value="${user.login}"/></b></td>
                </tr>
                <tr>
                    <th>Password</th>
                    <td><b><c:out value="${user.password}"/></b></td>
                </tr>
                <tr>
                    <th>Role</th>
                    <td><b>
                        <c:choose>
                            <c:when test="${user.isAdmin}">
                                Admin
                            </c:when>
                            <c:otherwise>
                                User
                            </c:otherwise>
                        </c:choose>
                    </b></td>
                </tr>
            </table>
        </div>
    </div>
</div>

</body>
</html>
