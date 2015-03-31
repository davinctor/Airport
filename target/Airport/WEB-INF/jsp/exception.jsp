<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<jsp:include page="fragments/staticFiles.jsp"/>

<body>
<div class="container">

    <h2>Something happened...</h2>

    <p>${exception.message}</p>

    <div class="panel panel-default">
        <div class="panel-body">
            <c:forEach items="${exception.stackTrace}" var="stackTrace">
                ${stackTrace}
            </c:forEach>
        </div>
    </div>


</div>
</body>

</html>