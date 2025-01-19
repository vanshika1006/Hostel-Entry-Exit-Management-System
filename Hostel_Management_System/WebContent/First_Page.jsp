<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hostel Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
    
        .error-message {
            color: red;
            position: absolute;
            top: 10px;
            left: 10px;
        }
    
        body {
            color: #fff;
        }

        header {
            background-color: #3498db;
            text-align: center;
            padding: 20px;
            padding-bottom: 40px;
        }

        h1 {
            color: #ffffff;
            font-size: 36px;
            padding-top: 10px;
            margin-bottom: 0;
        }

        .container {
            color: #fff;
            background-color: rgba(231, 35, 172, 0.7);
            align-items: center;
            padding: 20px;
            text-align: center;
            border-radius: 10px;
            margin-top: 20px;
        }

        .container2 {
            color: #000;
            background-color: rgba(35, 202, 231, 0.7);
            align-items: left;
            padding: 20px;
            text-align: left;
            border-radius: 10px;
            margin: 20px auto;
            max-width: 600px;
        }

        form {
            margin-top: 20px;
        }

        .registration-link {
            color: #fff ;
            text-decoration: none; 
            font-size: 24px
        }

        .registration-link:hover {
            text-decoration: underline; 
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-md bg-dark navbar-dark">
        <div class="navbar-collapse justify-content-end">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/new" class="nav-link registration-link">New Registration</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="error-message">
        <c:if test="${not empty errorMessage}">
            ${errorMessage}
        </c:if>
    </div>

    <header>
        <h1>J.C.BOSE UNIVERSITY , YMCA , FARIDABAD</h1>
    </header>

    <div class="container">
        <h2>HOSTEL ENTRY-EXIT MANAGEMENT SYSTEM</h2>
    </div>

    <div class="container2">
        <form action="login" method="post">
            <div class="mb-3">
                <label for="id" class="form-label">ID</label>
                <input type="text" class="form-control" id="id" name="id">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
   

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
