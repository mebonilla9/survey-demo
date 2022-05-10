<%--
    Document   : index
    Created on : 05 may. 2022, 13:38:01
    Author     : dev_manuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JSP Page</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  <c:if test="${sessionScope.theme == 'claro'}">
    <link rel="stylesheet" href="/survey-demo/css/light.css">
  </c:if>
  <c:if test="${sessionScope.theme == 'oscuro'}">
    <link rel="stylesheet" href="/survey-demo/css/dark.css">
  </c:if>
</head>
<body>
<%-- jsp comment --%>
<div class="container-fluid">
  <div class="composed-div">
    <h1>Little software development Survey results</h1>
  </div>
  <div class="composed-div">
    <div class="container-fluid align-items-center">
      <div class="row">
        <h3 class="col-12">Años de experiencia</h3>
        <table class="table table-striped table-hover">
          <thead>
          <th>Valor</th>
          <th>Ocurrencias</th>
          </thead>
          <tbody>
          <c:forEach items="${years}" var="year">
            <tr>
              <td><c:out value="${year.key}"/></td>
              <td><c:out value="${year.value}"/></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
      <div class="row">
        <h3 class="col-12">Profesiones</h3>
        <table class="table table-striped table-hover">
          <thead>
          <th>Valor</th>
          <th>Ocurrencias</th>
          </thead>
          <tbody>
          <c:forEach items="${professions}" var="profession">
            <tr>
              <td><c:out value="${profession.key}"/></td>
              <td><c:out value="${profession.value}"/></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
      <div class="row">
        <h3 class="col-12">Tecnologias del FrontEnd</h3>
        <table class="table table-striped table-hover">
          <thead>
          <th>Valor</th>
          <th>Ocurrencias</th>
          </thead>
          <tbody>
          <c:forEach items="${frontEnds}" var="frontend">
            <tr>
              <td><c:out value="${frontend.key}"/></td>
              <td><c:out value="${frontend.value}"/></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
      <div class="row">
        <h3 class="col-12">Tecnologias del Backend</h3>
        <table class="table table-striped table-hover">
          <thead>
          <th>Valor</th>
          <th>Ocurrencias</th>
          </thead>
          <tbody>
          <c:forEach items="${backEnds}" var="backend">
            <tr>
              <td><c:out value="${backend.key}"/></td>
              <td><c:out value="${backend.value}"/></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
      <div class="row">
        <h3 class="col-12">Motores de bases de datos</h3>
        <table class="table table-striped table-hover">
          <thead>
          <th>Valor</th>
          <th>Ocurrencias</th>
          </thead>
          <tbody>
          <c:forEach items="${dataBases}" var="database">
            <tr>
              <td><c:out value="${database.key}"/></td>
              <td><c:out value="${database.value}"/></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
          integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
          crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
          integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
          crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
          integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
          crossorigin="anonymous"></script>
</body>
</html>
