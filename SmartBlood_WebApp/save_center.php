<?php
    include('session.php');
    
if(isset($_POST["save"])){
	require_once("connection/config.php");
	$center=$db->real_escape_string($_POST["center_name"]);
	$addr=$db->real_escape_string($_POST["center_address"]);
	$tel=$db->real_escape_string($_POST["center_tel"]);
			$query=$db->query (" INSERT INTO center (centerName, phone, address) VALUES('$center', '$tel', '$addr' )") or die($db->error );
			if ($query) {
				header("location:register_center.php?status=Record Updated Successfully");
			}else{
				header("location:register_center.php?status=Failed to record");
			}
}
?>