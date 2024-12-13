<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h3>Authentication</h3>
    <form action="${pageContext.request.contextPath}/loginServlet" method="post">
        Enter your username:
        <br/>
        <input type="text" name="username"/>
        <br/>
        Password:
        <br/>
        <input type="password" name="password"/>
        <br/>
        <input type="submit" value="Login"/>
    </form>
</body>
</html>
