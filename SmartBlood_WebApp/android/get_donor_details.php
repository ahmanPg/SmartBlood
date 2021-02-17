<?php
include '../connection/config.php';
$donorArray = array();
$response = array();
//Check for mandatory parameter movie_id
if(isset($_GET['donor_id'])){
	$donorId = $_GET['donor_id'];
	//Query to fetch movie details
	$query = "SELECT CONCAT(fname,' ',lname) as fullName, email, bloodType, gender, phone, birthDate, CONCAT(district,', ',province) as Address FROM donors INNER JOIN users WHERE donorID=?";
	if($stmt = $db->prepare($query)){
		//Bind movie_id parameter to the query
		$stmt->bind_param("i",$donorId);
		$stmt->execute();
		//Bind the fetched data to $donorID,$fullName, $bloodType, $birthDate, $gender, $district
		$stmt->bind_result($donorName,$email,$donorGroup,$donorSex,$donorPhone,$dob,$donorAddress);
		//Check for results		
		if($stmt->fetch()){
			//Populate the movie array
		$donorArray["donor_id"] = $donorId;
		$donorArray["donor_name"] = $donorName;
		$donorArray["donor_email"] = $email;
		$donorArray["donor_dob"] = $dob;
		$donorArray["donor_group"] = $donorGroup;
		$donorArray["donor_sex"] = $donorSex;
		$donorArray["donor_location"] = $donorAddress;
		$donorArray["donor_phone"] = $donorPhone;
		$response["success"] = 1;
		$response["data"] = $donorArray;
		
		}else{
			//When movie is not found
			$response["success"] = 0;
			$response["message"] = "Donor not found";
		}
		$stmt->close();
 
 
	}else{
		//Whe some error occurs
		$response["success"] = 0;
		$response["message"] = mysqli_error($db);
		
	}
 
}else{
	//When the mandatory parameter movie_id is missing
	$response["success"] = 0;
	$response["message"] = "missing parameter donor_id";
}
//Display JSON response
echo json_encode($response);
?>
