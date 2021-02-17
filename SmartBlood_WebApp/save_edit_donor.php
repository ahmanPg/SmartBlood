<?php
include('session.php');

if(isset($_POST["save"])){
	require_once("connection/config.php");
	$donor_id=$db->real_escape_string($_POST["donorID"]);
	$fname=$db->real_escape_string($_POST["fname"]);
	$lname=$db->real_escape_string($_POST["lname"]);
	$tel=$db->real_escape_string($_POST["tel"]);
	$sex=$db->real_escape_string($_POST["sex"]);
	$status=$db->real_escape_string($_POST["status"]);
	$group=$db->real_escape_string($_POST["group"]);
	$mass=$db->real_escape_string($_POST["mass"]);
	$district=$db->real_escape_string($_POST["district"]);
	$province=$db->real_escape_string($_POST["province"]);
	$dob=$db->real_escape_string($_POST["dob"]);

	
	$userstr="UPDATE users SET status='$status' WHERE userID='$donorID'";
	$donorstr="UPDATE donors SET fname='$fname', lname='$lname', phone='$tel', bloodType='$group', weight='$mass', gender='$sex', district='$district', province='$province', birthDate='$dob' WHERE donorID='$donor_id'";

	$userupdate=$db->query($userstr) or die($db->error );
			if($userupdate){
					$donorupdate=$db->query($donorstr) or die($db->error );
				if($donorupdate){
					header("location:view_donors.php?status=Donor Updated Successfully");
				}else{
					header("location:edit_donor.php?error=Update Failed");

				}

			}
	
	
}
?>