<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hostel Management System</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #333;
            padding: 10px;
            color: white;
            text-align: center;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .button-container {
            text-align: center;
        }

        form {
            display: inline-block;
            margin: 10px;
        }

        input[type="submit"], button {
            background-color: #333;
            color: white;
            padding: 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-bottom: 15px;
            width: 200px; 
        }

        input[type="submit"]:hover, button:hover {
            background-color: #555;
        }
    </style>
</head>
<body>
    <header>
        <h1>Hello ${user.name}</h1>
    </header>

    <div class="container">
        <div class="button-container">
            <form action="EntryExitServlet" method="post">
                <input type="hidden" name="id" value="${id}">
                <input type="hidden" name="action" value="entry">
                <button type="submit">Record Entry</button>
            </form>

            <form action="EntryExitServlet" method="post">
                <input type="hidden" name="id" value="${id}">
                <input type="hidden" name="action" value="exit">
                <button type="submit">Record Exit</button>
            </form>

            <form action="studentRecords" method="get">
                <input type="hidden" name="id" value="${id}">
                <button type="submit">Show Previous Records</button>
            </form>
        </div>
    </div>
</body>
</html>
