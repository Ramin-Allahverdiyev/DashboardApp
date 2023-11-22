<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>Employee APP</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
  <h2 style="text-align: center">Statistics</h2>
  <div class="container text-left">
    <a href="<%=request.getContextPath()%>/user/employee" class="btn btn-success">My data</a>
    <a href="<%=request.getContextPath()%>/user/employee/actions?action=allemp" class="btn btn-success">All Employees</a>
    <a href="<%=request.getContextPath()%>/user/employee/actions?action=statistics" class="btn btn-success">Statistics</a>
    <br>

  </div>
  <br>
  <table class="table table-bordered">
    <thead>
    <tr>
      <th>Department</th>
      <th>Number OF Staff</th>
      <th>Percentage <i>(Rounded)</i></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="sts" items="${stats}">
      <tr>
        <td><c:out value="${sts.department}" /></td>
        <td><c:out value="${sts.numdepemp}" /></td>
        <fmt:formatNumber value="${sts.percentageOfDep}" pattern="#0.00" var="formattedPercentage" />
        <td><c:out value="${formattedPercentage}" /> %</td>
      </tr>
    </c:forEach>
    </tbody>
  </table><br>
  <b>Number Of All Employees: <c:out value="${numofemp}" /> </b>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
