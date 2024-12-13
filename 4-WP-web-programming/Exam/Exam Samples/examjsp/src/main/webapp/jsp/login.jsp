<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>
    <div class="login-box">
        <h3>Login</h3>
        <form action="${pageContext.request.contextPath}/loginServlet" method="post">
            <label>Username: </label>
            <input type="text" name="username"/>

            <br/>

            <label>Password:</label>
            <input type="password" name="password"/>

            <br/>

            <input type="submit" value="Login"/>
        </form>
        <a href="register.jsp">Register Here!</a>
    </div>
</body>
</html>
