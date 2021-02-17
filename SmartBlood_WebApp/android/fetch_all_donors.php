<?php
include '../connection/config.php';
//Query to from donors table
$result = array();
$donorArray = array();
$response = array();
//Prepare the query
if (isset($_GET['group'])) {
	$group_choice = $_GET['group'];
	$district_choice = $_GET['district'];
	$query = "SELECT donorID, CONCAT(fname,' ',lname) as fullName,  bloodType, weight, (YEAR(NOW())-YEAR(birthDate)) as age, CONCAT(district,', ',province) as Address, gender FROM donors WHERE bloodType =? and district = ?" ;

	if($stmt = $db->prepare($query)){
		$stmt->bind_param("ss", $group_choice, $district_choice);
		$stmt->execute();
		//Bind the fetched data to $donorID,$fullName, $bloodType, $birthDate, $gender, $district
		$stmt->bind_result($donorID,$fullName, $bloodType, $birthDate, $donorAge, $district, $gender);
		//Fetch 1 row at a time					
		while($stmt->fetch()){
			//Populate the movie array
			$donorArray["donor_id"] = $donorID;
			$donorArray["donor_name"] = $fullName;
			$donorArray["donor_group"] = $bloodType;
			$donorArray["donor_dob"] = $birthDate;
			$donorArray["donor_age"] = $donorAge;
			$donorArray["donor_sex"] = $gender;
			$donorArray["donor_location"] = $district;

			$result[]=$donorArray;
			
		}
		$stmt->close();
		$response["success"] = 1;
		$response["data"] = $result;
		
	 
	}else{
		//Some error while fetching data
		$response["success"] = 0;
		$response["message"] = mysqli_error($db);
			
		
	}
}
//Display JSON response
echo json_encode($response);
 