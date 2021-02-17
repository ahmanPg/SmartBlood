<?php
    include('session.php');

if(isset($_POST["save"])){
	require_once("connection/config.php");
	$donor = $db->real_escape_string($_POST["donor"]);
	$bp = $db->real_escape_string($_POST["bp"]);
	$haem = $db->real_escape_string($_POST["haem"]);
	$pulse = $db->real_escape_string($_POST["pulse"]);
	$center = $_POST["center"];
	
	$query = $db->query ("INSERT INTO donation (donationNo, donorID, centerName, docID, DonatedDate, bloodPressure, haemContent, pulse) VALUES('','$donor','$center', 3,NOW(),'$bp', '$haem', '$pulse'  )") or die($db->error );
	if ($query) {
		header("location:record_donation.php?status=Record Updated Successfully");
	}else{
		header("location:record_donation.php?status=Recording Has Failed");
	}
}
?>