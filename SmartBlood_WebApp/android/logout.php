<?php
session_start();
$response = array();
unset($_SESSION["LOGGED_USER"]);
unset($_SESSION["USER_ROLE"]);
session_destroy();
$response["success"] = 1;
echo json_encode($response);

?>