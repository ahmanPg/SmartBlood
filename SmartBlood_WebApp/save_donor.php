<?php
session_start();
if(!isset($_SESSION["login_user"])){
	header("location:index.php");
}
if(isset($_POST["save"])){
	require_once("connection/config.php");
	$dNo=$db->real_escape_string($_POST["donorNo"]);
	$fname=$db->real_escape_string($_POST["fname"]);
	$lname=$db->real_escape_string($_POST["lname"]);
	$tel=$db->real_escape_string($_POST["tel"]);
	$group=$db->real_escape_string($_POST["group"]);
	$mass=$db->real_escape_string($_POST["mass"]);
	$status=$db->real_escape_string($_POST["status"]);
	$sex=$db->real_escape_string($_POST["sex"]);
	$district=$db->real_escape_string($_POST["location"]);
	$dob=$db->real_escape_string($_POST["dob"]);
	$password=sha1($fname);
	$check=$db->query("SELECT *from users WHERE userName='$fname'") or die($db->error );
	$rows=mysqli_num_rows($check);
	if($rows==0){
			$db->query("INSERT INTO users (userName,passID,utype,status, createdAt, updatedAt) VALUES('$fname','$password',2,'$status', NOW(), NOW())") or die($db->error );
			$selected=$db->query("SELECT userID from users where userName='$fname'") or die($db->error);
			echo "$fname";
			$selected_row=mysqli_fetch_array($selected);
			$user= $selected_row['userID'];
			if ($selected_row) {
				$donorinsert=$db->query("INSERT INTO donors (donorID, donorNo, fname, lname, phone, bloodType, weight, gender, district, birthDate, profile) VALUES ('$user', '$dNo', '$fname', '$lname', '$tel', '$group', '$mass', '$sex', '$district', '$dob', 'default.png')") or die($db->error );
				if ($donorinsert) {
					header("location:register_donor.php?status=Resistration Successfully");
				}else{
					header("location:register_donor.php?error=Resistration Failed");

				}
			}

	}else{
		header("location:register_donor.php?error=Registration Number Already Exists");
	}
			
}