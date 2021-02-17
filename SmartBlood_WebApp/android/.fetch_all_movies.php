<?php
include 'db_connect.php';
//Query to select movie id and movie name
$query = "SELECT movie_id, movie_name FROM movies";
$result = array();
$movieArray = array();
$response = array();
//Prepare the query
if($stmt = $con->prepare($query)){
	$stmt->execute();
	//Bind the fetched data to $movieId and $movieName
	$stmt->bind_result($movieId,$movieName);
	//Fetch 1 row at a time					
	while($stmt->fetch()){
		//Populate the movie array
		$movieArray["movie_id"] = $movieId;
		$movieArray["movie_name"] = $movieName;
		$result[]=$movieArray;
		
	}
	$stmt->close();
	$response["success"] = 1;
	$response["data"] = $result;
	
 
}else{
	//Some error while fetching data
	$response["success"] = 0;
	$response["message"] = mysqli_error($con);
		
	
}
//Display JSON response
echo json_encode($response);
 
<?php
include 'config.php';
//Query to from donors table
$query = "SELECT donorID, concat(fname, " ", lname) as fullName,  bloodType, birthDate, gender, district FROM donors";
$result = array();
$donorArray = array();
$response = array();
//Prepare the query
if($stmt = $con->prepare($query)){
	$stmt->execute();
	//Bind the fetched data to $donorID,$fullName, $bloodType, $birthDate, $gender, $district
	$stmt->bind_result($donorID,$fullName, $bloodType, $birthDate, $gender, $district);
	//Fetch 1 row at a time					
	while($stmt->fetch()){
		//Populate the movie array
		$donorArray["donor_id"] = $donorID;
		$donorArray["donor_name"] = $fullName;
		$donorArray["donor_group"] = $bloodType;
		$donorArray["donor_dob"] = $birthDate;
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
	$response["message"] = mysqli_error($con);
		
	
}
//Display JSON response
echo json_encode($response);
 
