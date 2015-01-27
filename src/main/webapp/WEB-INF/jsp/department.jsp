<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="fragments/staticFiles.jsp"/>
    <title>${department.name}</title>
</head>
<body>
<jsp:include page="fragments/navbar.jsp"/>
<div class="container-fluid text-center">
    <div class="page-header row">
        <div class="col-lg-8 col-lg-offset-2">
            <h2>
                ${department.name}
                <small>
                    <a href="/department/edit/${department.id}" class="btn btn-default"
                       data-original-title="Редактировать отдел" data-toggle="tooltip">
                        <span class="fa fa-pencil"></span>
                    </a>
                    <a href="" resource="/department/delete/${department.id}" class="btn btn-default"
                       role="button" data-toggle="modal" data-target="#deleteModal">
            <span class="fa fa-eraser"
                  data-original-title="Удалить отдел" data-toggle="tooltip"></span>
                    </a>
                </small>
            </h2>
        </div>
    </div>
    <div class="col-md-offset-4 col-md-4 row">
        <ul class="list-group">
            <dl class="dl-horizontal">
                <li class="list-group-item">
                    <dt>Название отдела</dt>
                    <dd>${department.name}</dd>
                </li>
                <li class="list-group-item">
                    <dt>Режим работы</dt>
                    <dd>
                        <span class="fa fa-clock-o"></span>
                        ${department.scheduleFrom} : ${department.scheduleTo}
                    </dd>
                </li>
                <li class="list-group-item">
                    <dt>Перерыв</dt>
                    <dd>
                        <span class="fa fa-coffee"></span>
                        ${department.breakFrom} : ${department.breakTo}
                    </dd>
                </li>
                <li class="list-group-item">
                    <dt>Номер телефона</dt>
                    <dd>${department.phoneNum}</dd>
                </li>
                <li class="list-group-item">
                    <dt>Персонал</dt>
                    <dd>
                        <a href="/search?department=${department.name}" class="btn btn-info">
                            Посмотреть список персонала
                        </a>
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
