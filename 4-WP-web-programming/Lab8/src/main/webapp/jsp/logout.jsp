<%--
  Created by IntelliJ IDEA.
  User: csoka
  Date: 5/25/2020
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate();
    response.sendRedirect("../index.html");
%>
<html>
<head>
    <title>Logout</title>
</head>
<body>
    <div>
        Redirecting...
    </div>
</body>
</html>
