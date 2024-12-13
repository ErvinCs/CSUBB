<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>
    <div class="login-box">
        <h3>Register</h3>
        <form action="${pageContext.request.contextPath}/registerServlet" method="post">
            <label>Username: </label>
            <input type="text" name="username"/>

            <br/>

            <label>Password:</label>
            <input type="password" name="password"/>

            <br/>

            <input type="submit" value="Register"/>
        </form>
        <a href="login.jsp">Login Here!</a>
    </div>
</body>
</html>
