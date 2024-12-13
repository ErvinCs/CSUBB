<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate();
    response.sendRedirect("../index.html");
%>
<html>
<head>
    <title>Logout</title>
    <link rel="stylesheet" type="text/css" href="../css/redirect.css">
</head>
<body>
    <div>
        <h2>Redirecting...</h2>
    </div>
</body>
</html>
