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

    $user = $controller->getUserByUsername($username);

    if ($user->getId() < 0) {
        $controller->addUser($username, $password);

        $user = $controller->getUser($username, $password);

        // Store session data
        $_SESSION["Loggedin"] = true;
        $_SESSION["UserId"] = $user->getId();
        $_SESSION["Username"] = $user->getUsername();
        $_SESSION["Cart"] = array();

        // Redirect to index page
        header("Location: index.php");
    } else {
        // Redirect to error page
        header("Location: invalidRegister.html");
    }
}
?>