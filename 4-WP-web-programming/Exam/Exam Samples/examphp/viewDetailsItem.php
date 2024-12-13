<!DOCTYPE html>
<html lang="en">
<head>
    <title>Item</title>
    <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
    <script src="lib/jquery-2.0.3.js"></script>
    <script lang="javascript">
        $(document).ready(function() {
            $("#update-btn").click(function () {
                var thisItemId = $("#item_id").attr("value");
                $.getJSON('controller.php', {
                    action: 'updateItem',
                    itemId: thisItemId,
                    name: $("#name").attr("value"),
                    description: $("#desc").attr("value"),
                    value: $("#value").attr("value")
                });
            });
            $("#delete-btn").click(function () {
                var thisItemId = $("#item_id").attr("value");
                $.getJSON('controller.php', {
                        action: 'removeItem',
                        itemId: thisItemId
                    });
            });
            $('#name').on("input", function() {
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
<h2>Item: #<?php echo htmlspecialchars($_GET["item_id"]); ?></h2>
<br/>

<div>
    <input id="name" type="text" value="<?php echo htmlspecialchars($_GET["name"]); ?>">
    <input id="desc" type="text" value="<?php echo htmlspecialchars($_GET["description"]); ?>">
    <input id="value" type="text" value="<?php echo htmlspecialchars($_GET["value"]); ?>">

    <input id="update-btn" type="button" value="Update">
    <br/>
    <input id="delete-btn" type="button" value="Delete">
    <br/>
</div>
<br/>

<section>
    <input id="items-btn" type="button" value="Items" onclick="document.location.href='viewItems.html'">
    <br/>
    <input id="back-btn" type="button" value="Admin" onclick="document.location.href='viewAdminItems.html'">
    <br/>
    <input id="log-out-btn" type="button" value="Log Out" onclick="document.location.href='logout.php'">
    <br/>
</section>

<input type="hidden" id="item_id" name="item_id" value="<?php echo htmlspecialchars($_GET["item_id"]); ?>">
</body>
</html>