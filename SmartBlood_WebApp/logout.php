<?php
session_start();
unset($_SESSION["LOGGED_USER"]);
unset($_SESSION["USER_ROLE"]);
session_destroy();
header("location:index.php");

?>