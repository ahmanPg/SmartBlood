<?php
require("session.php");

if(isset($_POST["save"])){
	require_once("connection/config.php");
	$dNo=$db->real_escape_string($_POST["donationNo"]);
	$bp=$db->real_escape_string($_POST["bp"]);
	$haem=$db->real_escape_string($_POST["haem"]);
	$center=$db->real_escape_string($_POST["center"]);

	$donation_update=$db->query("UPDATE donation SET bloodPressure='$bp', haemContent='$haem', centerName='$center' WHERE donationNo='$dNo'") or die($db->error );
			if($donation_update){

					header("location:view_donations.php?status=Donation Updated Successfully");
			}else{
					header("location:edit_donation.php?error=Update Failed");

			}
	
}
?>