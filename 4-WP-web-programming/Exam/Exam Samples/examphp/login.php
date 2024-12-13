<?php
require_once 'controller.php';
require_once 'model.php';
require_once 'user.php';

session_start();

// Check if the user is already logged in and redirect him to index.php
if(isset($_SESSION["Loggedin"]) && $_SESSION["Loggedin"] === true) {
    header("Location: index.php");
    exit;
}

// Init vars
$username = $password = "";

// Process form data when form is submitted
if($_SERVER["REQUEST_METHOD"] == "POST"){
    $username = trim($_POST["username"]);
    $password = trim($_POST["password"]);

    $user = $controller->getUser($username, $password);

    if ($user->getId() > -1) {
        $username = $user->getUsername();
        $password = $user->getPassword();
        $id       = $user->getId();

        // Store session data
        $_SESSION["Loggedin"] = true;
        $_SESSION["UserId"] = $id;
        $_SESSION["Username"] = $username;
        $_SESSION["Cart"] = array();

        // Redirect to index page
        header("Location: index.php");
    } else {
        // Redirect to error page
        header("Location: invalidLogin.html");
    }
}
?>
