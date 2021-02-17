<?php
include '../connection/config.php';
//Query to select movie id and movie name
$query = "SELECT centerName FROM center";
$result = array();
$centerArray = array();
$response = array();
//Prepare the query
if($stmt = $db->prepare($query)){
	$stmt->execute();
	//Bind the fetched data to $movieId and $movieName
	$stmt->bind_result($centerName);
	//Fetch 1 row at a time					
	while($stmt->fetch()){
		//Populate the movie array
		$centerArray["center_name"] = $centerName;
		$result[]=$centerArray;
		
	}
	$stmt->close();
	$response["success"] = 1;
	$response["data"] = $result;
	
 
}else{
	//Some error while fetching data
	$response["success"] = 0;
	$response["message"] = mysqli_error($db);
}
//Display JSON response
echo json_encode($response);
?>