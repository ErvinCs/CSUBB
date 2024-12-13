<?php
// Initialize the session
session_start();

// Unset all the session vars
$_SESSION = array();

// Destroy the session.
session_destroy();

// Redirect to login page
header("Location: login.html");
die();
?>