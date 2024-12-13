<?php
require_once 'controller.php';
require_once 'model.php';
require_once 'user.php';

session_start();
//TODO Redo this or at least remove useless parts

// Check if the user is already logged in and redirect him to index.php
if(isset($_SESSION["loggedin"]) && $_SESSION["loggedin"] === true) {
    header("Location: index.php");
    exit;
}

// Init vars
$username = $password = "";
$username_err = $password_err = "";

// Process form data when form is submitted
if($_SERVER["REQUEST_METHOD"] == "POST"){

    // Check if username is empty
    if(empty(trim($_POST["username"]))){
        $username_err = "Please enter a username.";
    } else{
        $username = trim($_POST["username"]);
    }

    // Check if password is empty
    if(empty(trim($_POST["password"]))){
        $password_err = "Please enter a password.";
    } else{
        $password = trim($_POST["password"]);
    }

    // If there are no errors then check credentials
    if(empty($username_err) && empty($password_err)) {
        $user = $controller->getUser($username, $password);

        if ($user->getId() !== -1) {
            $username = $user->getUsername();
            $password = $user->getPassword();
            $id = $user->getId();

            // Store session data
            $_SESSION["loggedin"] = true;
            $_SESSION["id"] = $id;
            $_SESSION["username"] = $username;
            $_SESSION["cart"] = array();

            // Redirect to index page
            header("Location: index.php");
        } else {
            // TODO: Show err msg
            header("Location: login.html");
        }
    }
}
?>