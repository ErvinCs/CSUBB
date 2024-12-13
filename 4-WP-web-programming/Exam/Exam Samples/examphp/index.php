<?php

// Start the session
session_start();

// Check if the user is logged in, if not then redirect him to the login page
if(!isset($_SESSION["Loggedin"]) || $_SESSION["Loggedin"] !== true){
    header("Location: login.html");
    exit;
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Web Programming Exam</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
    <div class="link-container">
        <a href="viewItems.html">View My Items</a>
        <br/>
        <a href="viewAdminItems.html">Admin Items</a>
        <br/>
        <a href="logout.php">Logout</a>
        <br/>
    </div>
</body>
</html>