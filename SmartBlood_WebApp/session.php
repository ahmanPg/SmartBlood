<?php
   	require('connection/config.php');
   	require('loginHandler.php');
   	$user_check = $_SESSION['login_user'];
   
  	$ses_sql = $db->query("select userName from users where userName = '$user_check' ");
   
   	$row = mysqli_fetch_array($ses_sql,MYSQLI_ASSOC);
   
   	$login_session = $row['userName'];
   
   	if(!isset($_SESSION['login_user'])){
      header("location:index.php?error=Oops! You have to login first");
   }
?>