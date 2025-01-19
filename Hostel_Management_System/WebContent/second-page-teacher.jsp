<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teacher Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #498689;
            color: white;
            text-align: center;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            background-color: black;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .btn-container {
            text-align: center;
            margin-top: 20px;
        }

        .btn-custom {
            font-size: 18px;
            padding: 15px 30px;
        }
    </style>
</head>
<body>

<div class="container">
    <header>
        <h1>Welcome ${user.name}</h1>
    </header>

    <div class="btn-container">
        <a href="GetByDate.jsp" class="btn btn-primary btn-custom mr-2">Get By Date</a>
        <a href="GetById.jsp" class="btn btn-success btn-custom">Get By ID</a>
    </div>
</div>

</body>
</html>
