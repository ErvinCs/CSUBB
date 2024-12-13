<?php

// Start the session
session_start();

// Check if the user is logged in, if not then redirect him to the login page
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("Location: login.html");
    exit;
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
</head>
<body>
    <div>
        <!-- Why tho -->
        <h1>Welcome <b><?php echo($_SESSION["username"]); ?></b></h1>
    </div>
    <p>
        <a href="logout.php">Sign Out</a>
    </p>
    <a href="products.html">Browse Products</a>
</body>
</html>