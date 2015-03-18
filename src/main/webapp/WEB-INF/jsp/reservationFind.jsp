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
<div class="col-md-offset-2 col-md-8  page-header row">
  <div class=" text-center">
    <h2>
      Бронирование
    </h2>
  </div>
</div>
<div class="page-body container-fluid text-center">
  <div class="row col-md-offset-2 col-md-8">
    <form class="form-horizontal" action="/reservation/new" method="post">
      <div class="panel panel-default">
        <div class="panel-body">
          <div class="form-group">
            <div class="col-md-6">
              <label>Укажите место отправления</label>
              <div class="input-group">
                <span class="input-group-addon">Откуда</span>
                <input type="text" class="form-control" placeholder="Место вылета...">
              </div>
            </div>
            <div class="col-md-6">
              <label>Укажите место направления</label>
              <div class="input-group">
                <span class="input-group-addon">Куда</span>
                <input type="text" class="form-control" placeholder="Место прибытия...">
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="col-md-3">
              <label>Укажите время прибытия</label>
              <div class="input-group date" id="datepickerfrom">
                <input type="text" class="form-control" placeholder="Время отбытия...">
              <span class="input-group-addon">
                <span class="glyphicon glyphicon-calendar"></span>
              </span>
              </div>
            </div>
            <div class="col-md-3">
              <label>Укажите время отбытия</label>
              <div class="input-group date" id="datepickerto">
                <input type="text" class="form-control" placeholder="Время прибытия...">
              <span class="input-group-addon">
                <span class="glyphicon glyphicon-calendar"></span>
              </span>
              </div>
            </div>
            <div class="col-md-2">
              <label for="spinner-infant">Младенцев</label>
              <div class="input-group number-spinner">
              <span class="input-group-btn data-dwn">
                <a class="btn btn-default" data-dir="dwn" for="spinner-infant">
                  <span class="glyphicon glyphicon-minus"></span>
                </a>
              </span>
                <input type="text" class="form-control text-center" value="0" min="0" max="3" id="spinner-infant" readonly>
              <span class="input-group-btn data-up">
                <a class="btn btn-default" data-dir="up" for="spinner-infant">
                  <span class="glyphicon glyphicon-plus"></span>
                </a>
              </span>
              </div>
            </div>
            <div class="col-md-2">
              <label for="spinner-child">Детей</label>
              <div class="input-group number-spinner">
              <span class="input-group-btn data-dwn">
                <a class="btn btn-default" data-dir="dwn" for="spinner-child">
                  <span class="glyphicon glyphicon-minus"></span>
                </a>
              </span>
                <input type="text" class="form-control text-center" value="0" min="0" max="3" id="spinner-child" readonly>
              <span class="input-group-btn data-up">
                <a class="btn btn-default" data-dir="up"  for="spinner-child">
                  <span class="glyphicon glyphicon-plus"></span>
                </a>
              </span>
              </div>
            </div>
            <div class="col-md-2">
              <label for="spinner-adult">Взрослые</label>
              <div class="input-group number-spinner">
              <span class="input-group-btn data-dwn">
                <a class="btn btn-default" data-dir="dwn" for="spinner-adult">
                  <span class="glyphicon glyphicon-minus"></span>
                </a>
              </span>
                <input type="text" class="form-control text-center" value="0" min="0" max="3" id="spinner-adult" readonly>
              <span class="input-group-btn data-up">
                <a class="btn btn-default" data-dir="up" for="spinner-adult">
                  <span class="glyphicon glyphicon-plus"></span>
                </a>
              </span>
              </div>
            </div>
          </div>
        </div>
        <div class="panel-footer">
          <div class="text-right">
            <button class="btn btn-default btn-success">
              Поиск подходящего рейса <span class="fa fa-search"></span>
            </button>
          </div>
        </div>
      </div>
    </form>
  </div><!-- container-fluid -->
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>