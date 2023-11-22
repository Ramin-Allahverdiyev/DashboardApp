<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <title>Dashboard APP</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
  <h2 style="text-align: center">Dashboard</h2>
  <div class="container text-left">
    <a href="<%=request.getContextPath()%>/user/employee" class="btn btn-success">My data</a>
    <a href="<%=request.getContextPath()%>/user/employee/actions?action=allemp" class="btn btn-success">Members</a>
    <a href="<%=request.getContextPath()%>/user/employee/actions?action=statistics" class="btn btn-success">Statistics</a>
    <br>
    <br>
    <form action="<%=request.getContextPath()%>/user/filtered"  method="post">

      Department:
      <select class="form-control" name="departmentss" id="departmentss">
        <option value="IT">IT</option>
        <option value="HR">HR</option>
        <option value="Accounting">Accounting</option>
        <option value="Engineering">Engineering</option>
      </select><br>
      Gender:
      <select class="form-control" name="genderr" id="genderr">
        <option value="Female">Female</option>
        <option value="Male">Male</option>
      </select><br>
      <button type="submit" class="btn btn-secondary">Filter</button>
    </form>



  </div>
  <br>
  <table class="table table-bordered">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Surname</th>
      <th>Age</th>
      <th>Email</th>
      <th>Gender</th>
      <th>Department</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="empl" items="${emps}">
      <tr>
        <td><c:out value="${empl.id}" /></td>
        <td><c:out value="${empl.name}" /></td>
        <td><c:out value="${empl.surname}" /></td>
        <td><c:out value="${empl.age}" /></td>
        <td><c:out value="${empl.email}" /></td>
        <td><c:out value="${empl.gender}" /></td>
        <td><c:out value="${empl.department}" /></td>
        <c:if test="${empl.name eq sessionScope.name }">
          <td>
            <a href="<%=request.getContextPath()%>/user/employee/actions?action=edit&id=<c:out value='${empl.id}'/>" class="btn btn-success" style="color: black;border-color: whitesmoke;background: linear-gradient(0deg, darkorange, gold 80%)">Edit</a>
          </td>
        </c:if>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
