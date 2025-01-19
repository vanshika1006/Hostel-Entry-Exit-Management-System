<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Records</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa; 
            margin: 0;
            padding: 0;
            display: flex;
            align-items: top;
            justify-content: center;
            height: 100vh;
        }

        table {
            width: 80%;
            margin: 20px auto 0;
            background-color: #ffffff; 
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        table thead {
            background-color: #007bff; 
            color: white;
        }

        table th, table td {
            padding: 10px;
            text-align: center;
        }

        table tr:nth-child(even) {
            background-color: #f2f2f2; 
        }
    </style>
</head>
<body>

<div class="container">
    <h2 class="text-center mt-3 mb-3">Student Records</h2>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>TIME_IN</th>
            <th>TIME_OUT</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${records}">
            <tr>
                <td><c:out value="${student.id}" /></td>
                <td><c:out value="${student.time_in}" /></td>
                <td><c:out value="${student.time_out}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href= "First_Page.jsp">Back to Home Page</a><br>
<a href= "second-page-student.jsp">Back to My Page</a>
    
</div>

</body>
</html>
