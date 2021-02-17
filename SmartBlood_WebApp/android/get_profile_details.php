<?php
include '../connection/config.php';
$donorArray = array();
$response = array();
//Check for mandatory parameter movie_id
if(null !== $_GET['username'] && null !== $_GET['password']){
   $username = $_GET['username'];
   $password = sha1($_GET['password']);


   //Query to fetch movie details
   $query = "SELECT CONCAT(fname,' ',lname) as fullName, email, bloodType, gender, phone, birthDate, CONCAT(district,', ',province) as Address FROM donors INNER JOIN users ON users.userID = donors.donorID WHERE userName=? and passID =?";
   if($stmt = $db->prepare($query)){
      //Bind movie_id parameter to the query
      $stmt->bind_param("ss", $username, $password);
      $stmt->execute();
      //Bind the fetched data to $donorID,$fullName, $bloodType, $birthDate, $gender, $district
      $stmt->bind_result($donorName, $userMail, $donorGroup, $donorSex, $donorPhone, $birthDate, $donorAddress );
      //Check for results     
      if($stmt->fetch()){
         //Populate the user array
      // $donorArray["donor_id"] = $donorId;
      $donorArray["donor_name"] = $donorName;
      $donorArray["donor_group"] = $donorGroup;
      $donorArray["donor_sex"] = $donorSex;
      $donorArray["donor_location"] = $donorAddress;
      $donorArray["donor_phone"] = $donorPhone;
      $donorArray["user_email"] = $userMail;
      $donorArray["donor_dob"] = $birthDate;
      $response["success"] = 1;
      $response["data"] = $donorArray;
      
      }else{
         //When donor is not found
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
   //When the mandatory parameter donor_id is missing
   $response["success"] = 0;
   $response["message"] = "missing parameter donor_id";
}
//Display JSON response
echo json_encode($response);
?>
