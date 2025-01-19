<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>New Registration Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script>
    function clearForm() {
        document.getElementById("new-registeration-form.jsp").reset();
    }
</script>
    <style>
        body {
            background-color: #f8f9fa;
            color: #495057;
        }

        form {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }

        fieldset {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
        }

        button {
            background-color: #28a745;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <form action="./insert" method="post">
        <fieldset class="form-group">
            <label>Enter Name:</label>
            <input type="text" value="<c:out value=''/>" class="form-control" name="name" required="required">
        </fieldset>

        <fieldset class="form-group">
            <label>Enter Phone no. :</label>
            <input type="text" value="<c:out value=''/>" class="form-control" name="phone_no"
                required="required">
        </fieldset>

        <fieldset class="form-group">
            <label>Enter Email: </label>
            <input type="text" value="<c:out value=''/>" class="form-control" name="email"
                required="required">
        </fieldset>

        <fieldset class="form-group">
            <label>Enter ID: </label>
            <input type="text" value="<c:out value=''/>" class="form-control" name="id" required="required">
        </fieldset>

        <fieldset class="form-group">
            <label>Set Password: </label>
            <input type="password" value="<c:out value=''/>" class="form-control" name="password"
                required="required">
        </fieldset>

        <fieldset class="form-group">
            <label>User Type: (Enter S: For Student T: For Teacher) </label>
            <input type="text" value="<c:out value=''/>" class="form-control" name="userType"
                required="required">
        </fieldset>

        <button type="submit" class="btn btn-success" onclick="clearForm()">Save</button>
    </form>
</body>
</html>
