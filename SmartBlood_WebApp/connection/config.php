<?php
   define('DB_SERVER', 'localhost');
   define('DB_USERNAME', 'phpmyadmin');
   define('DB_PASSWORD', 'raha1994');
   define('DB_DATABASE', 'smbb');
   $db = new MySQLi(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);
if($db->connect_errno){
	echo "connection fail";
	echo "Error is ".$db->connect_error;
}
?>