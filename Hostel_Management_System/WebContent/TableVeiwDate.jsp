<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Records for Date</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #007bff;
            text-align: center;
        }

        table {
            margin-top: 20px;
        }

        th, td {
            text-align: center;
        }

        .table thead th {
            background-color: #007bff;
            color: white;
        }

        .table-bordered {
            border: 2px solid #007bff;
        }

        .table-bordered td, .table-bordered th {
            border: 2px solid #007bff;
        }

        .text-center {
            text-align: center;
        }

        .no-records-message {
            color: #868e96;
        }
    </style>
</head>
<body>

<div class="container">
    <h2 class="text-center mt-3 mb-3">Records for Date</h2>
    <c:if test="${not empty records}">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Time In</th>
                    <th>Time Out</th>
                </tr>
            </thead>
            <tbody>
                <%-- Loop through the records and display them --%>
                <c:forEach var="student" items="${records}">
                    <tr>
                        <td><c:out value="${student.id}" /></td>
                        <td><c:out value="${student.time_in}" /></td>
                        <td><c:out value="${student.time_out}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty records}">
        <p class="text-center no-records-message">No records available for the specified date.</p>
    </c:if>
    <a href= "First_Page.jsp">Back to Home Page</a><br>
    <a href= "second-page-teacher.jsp">Back to My Page</a>
    
</div>

</body>
</html>
