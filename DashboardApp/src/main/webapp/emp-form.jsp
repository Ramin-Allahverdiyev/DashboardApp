<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Employee Management</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
  <div class="row">
    <div class="col-3"></div>
    <div class="col-6">
      <div class="card mt-4">
        <div class="card-body">

          <form action="${pageContext.request.contextPath}/user/employee/actions?action=update&id=<c:out value='${emps.id}'/>" method="post">
            <caption>
              <h2>
                <c:if test="${emps != null}">
                  Edit Employee
                </c:if>
              </h2>
            </caption>

            <fieldset class="form-group">
              <b><label>Name</label></b>
              <input type="text" value="<c:out value='${emps.name}'/>" class="form-control"
                     name="name" required="required" >
            </fieldset>

            <fieldset class="form-group">
              <b><label>Surname</label></b>
              <input type="text" value="<c:out value='${emps.surname}'/>" class="form-control"
                     name="surname" required="required" >
            </fieldset>

            <fieldset class="form-group">
              <b><label>Age</label></b>
              <input type="text" value="<c:out value='${emps.age}'/>" class="form-control"
                     name="age" required="required" >
            </fieldset>

            <fieldset class="form-group">
              <b><label>Email</label></b>
              <input type="text" value="<c:out value='${emps.email}'/>" class="form-control"
                     name="email" required="required" >
            </fieldset>

            <fieldset class="form-group">
              <b><label>Department</label></b>
              <select class="form-control" name="department" id="department">
                <option value="IT">IT</option>
                <option value="HR">HR</option>
                <option value="Accounting">Accounting</option>
                <option value="Engineering">Engineering</option>
              </select>
            </fieldset>

            <fieldset class="form-group">
              <b><label>Gender</label></b>
              <input type="text" value="<c:out value='${emps.gender}'/>" class="form-control"
                     name="gender" required="required" >
            </fieldset>

            <fieldset class="form-group">
              <b><label>Password</label></b>
              <input type="text" value="<c:out value='${emps.password}'/>" class="form-control"
                     name="password" required="required" >
            </fieldset>

            <button type="submit" class="btn btn-success" style="color: black;background: linear-gradient(0deg, forestgreen, papayawhip 90%)">Submit</button>
          </form>
        </div>
      </div>
    </div>
    <div class="col-3"></div>
  </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>