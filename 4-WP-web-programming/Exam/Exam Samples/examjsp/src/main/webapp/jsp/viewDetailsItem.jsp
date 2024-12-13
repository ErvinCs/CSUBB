<%@ page import="ubb.exam.model.Item" %>
<%@ page import="ubb.exam.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Item currentItem = (Item) request.getAttribute("currentItem");
    User userOwner = (User) request.getAttribute("userOwner");
%>
<html>
<head>
    <title>Item</title>
    <script src="../lib/jquery-2.0.3.js"></script>
    <script lang="javascript">
        $(document).ready(function() {
            $("#update-btn").click(function () {
                var thisItemId = $("#item_id").attr("value");
                $.post('detailsItemServlet', {
                    operation: 'update',
                    itemId: thisItemId,
                    name: $("#name").attr("value"),
                    description: $("#desc").attr("value"),
                    value: $("#value").attr("value")
                });
                window.location.href = 'viewItems.jsp';
            });
            $("#delete-btn").click(function () {
                var thisItemId = $("#item_id").attr("value");
                $.post('detailsItemServlet', {
                    operation: 'delete',
                    itemId: thisItemId
                });
                window.location.href = 'viewItems.jsp';
            });
            $('#name').on("input", function() {
                //var input = this.value;
                //$("#name").text(input);
                $("#name").attr("value", this.value);
            });
            $('#desc').on("input", function() {
                $("#desc").attr("value", this.value);
            });
            $('#value').on("input", function() {
                $("#value").attr("value", this.value);
            });
        });

    </script>
</head>
<body>
    <h2>Item: #<%=currentItem.getId()%></h2>
    <h3>Owner: <%=userOwner.getUsername()%></h3>
    <br/>

    <div>
        <input id="name" type="text" value="<%=currentItem.getName()%>">
        <input id="desc" type="text" value="<%=currentItem.getDescription()%>">
        <input id="value" type="text" value="<%=currentItem.getValue()%>">

        <input id="update-btn" type="button" value="Update">
        <br/>
        <input id="delete-btn" type="button" value="Delete">
        <br/>
    </div>
    <br/>

    <section>
        <input id="items-btn" type="button" value="Items" onclick="document.location.href='viewItems.jsp'">
        <br/>
        <input id="back-btn" type="button" value="Menu" onclick="document.location.href='menu.jsp'">
        <br/>
        <input id="log-out-btn" type="button" value="Log Out" onclick="document.location.href='logout.jsp'">
        <br/>
    </section>

    <input type="hidden" id="user_id" name="user_id" value="<%=userOwner.getId()%>">
    <input type="hidden" id="item_id" name="item_id" value="<%=currentItem.getId()%>">
</body>
</html>
