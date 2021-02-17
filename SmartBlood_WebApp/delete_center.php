<?php
require_once("connection/config.php");

$id=$_GET["delete_id"];
$query=$db->query("DELETE FROM center where centerName='$id'") or die($db->error);
$row=$db->affected_rows;
if($row>0){
	header("location:view_center.php?status=Center Deleted Successfully");

}else{
header("location:view_center.php?error=Delete Failed");

}
?>