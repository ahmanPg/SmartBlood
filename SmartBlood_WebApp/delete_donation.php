<?php
require_once("connection/config.php");

$id=$_GET["delete_id"];
$query=$db->query("DELETE FROM donation where donationNo='$id'") or die($db->error);
$drow=$db->affected_rows;
if($drow>0){
	header("location:view_donations.php?status=Donation Deleted Successfully");

}else{
header("location:view_donation.php?error=Delete Failed");

}
?>