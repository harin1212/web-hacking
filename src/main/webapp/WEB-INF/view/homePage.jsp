<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <script type="text/javascript">
        window.onload = function() {
            var email = '${email}'; // Use JSP EL to insert the email value into the JavaScript code
            localStorage.getItem('email', email);
            console.log('Email stored in local storage:', email);
        };

    </script>
</head>
<body>
<h2>Welcome to the Home Page</h2>
<form action="/user" method="post">
    <input type="hidden" id="email" name="email" value="${email}"/>
    <button type="submit">View My Information</button>
</form>

<!-- User information display -->
<h3>User Details</h3>
<ul>
    <li>Username: ${user.username}</li>
    <li>Email: ${user.email}</li>
    <li>Email: ${user.password}</li>
</ul>

</body>
</html>
