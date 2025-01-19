<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Student Page</title>
</head>
<body>
<form action="StudentPage" method="post">
<table class = "table table-bordered">
<thead>
<tr>
<th>ID</th>
<th>Time IN</th>
<th>Time Out</th>
</tr>
</thead>
<tr>
<td>
<c:out value = "<%=session.getAttribute("id")%>"> 
</c:out></td>
<td>
<input type="hidden" name="entryTime" value="<%= new java.sql.Timestamp(System.currentTimeMillis()) %>">
</td>
<td>
<input type="hidden" name="exitTime" value="<%= new java.sql.Timestamp(System.currentTimeMillis()) %>">
</td>
</tr>
<input type="submit" value="Update">
</table>
</form>
</body>
</html>
