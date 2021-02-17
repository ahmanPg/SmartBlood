<?php
session_start();
if(isset($_POST["username"])){
	$response = array();
	require_once("../connection/config.php");

	$userName = $_POST["username"];
	$password = $_POST["password"];
	$email = $_POST["email"];
	$secQuestion = $_POST["question"];
	$answer = $_POST["answer"];

	$firstName = $_POST["fname"];
	$lastName = $_POST["lname"];
	$tel = $_POST["phone"];
	$group = $_POST["group"];
	$mass = $_POST["weight"];
	$sex = $_POST["sex"];
	$district = $_POST["district"];
	$province = $_POST["province"];
	$dob = $_POST["dob"];
	$newpassword = sha1($password);
	
	$check = $db->query("SELECT userID FROM users WHERE userName='$userName'") or die($db->error );
	$rows = mysqli_num_rows($check);	
	if($rows==0){
		$query = "INSERT INTO users (userName, email, passID, utype, status, createdAt, updatedAt, secQuestion, secAnswer) VALUES('$userName', '$email', '$newpassword', 2, 'active', NOW(), NOW(), '$secQuestion', '$answer')";
			if ($stmt = $db->prepare($query)) {
				// $stmt->bind_param("sssss", $userName, $email, $newpassword, $secQuestion,$secAnswer);
				$stmt->execute() or die($db->error);
				
				$selected=$db->query("SELECT userID FROM users WHERE userName='$userName'") or die($db->error);
				$selected_row=mysqli_fetch_array($selected);
				$donorId = $selected_row['userID'];
				if ($selected_row) {
					$query = "INSERT INTO donors (donorID, donorNo, fname, lname, phone, bloodType, weight, gender, birthDate, district, province, profile) VALUES ('$donorId', '', '$firstName', '$lastName', '$tel', '$group', '$mass', '$sex', '$dob', '$district', '$province', 'man.png')";
					if ($stmt = $db->prepare($query)) {
						// $stmt->bind_param("issssissss", $donorId, $firstName, $lastName, $tel, $group, $mass, $sex, $dob, $district, $province);
						$stmt->execute();

						if($stmt->affected_rows == 1){
							$response["success"] = 1;			
							$response["message"] = "Acount created successfully";
						}else{
							$response["success"] = 0;
							$response["message"] = mysqli_error($db);
						}	
					}else{
						$response["success"] = 0;
						$response["message"] = mysqli_error($db);
					}	
				}
			}else{
				$response["success"] = 0;
				$response["message"] = mysqli_error($db);
			}
	}else{
		$response["userID"] = $rows;
		$response["success"] = 2;
		$response["message"] = "Acount already exists";
	}
			
}else{
		$response["success"] = 0;
		$response["message"] = "missing mandatory parameters";
}

echo json_encode($response);

