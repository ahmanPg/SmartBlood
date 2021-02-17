<?php
require_once("connection/config.php");
if (isset($_GET['delete_id'])) {
	$id=$_GET["delete_id"];
	$query=$db->query("DELETE FROM users where userID='$id'") or die($db->error);
	$drow=$db->affected_rows;
	if($drow>0){
		header("location:view_donors.php?status=Donor Deleted Successfully");

	}else{
	header("location:view_donors.php?error=Delete Failed");

	}
}

?>