<?php
  require_once("connection/config.php");
  $donation="SELECT donationNo, donorID, concat(fname,' ', lname) as FullName, centerName, donatedDate, bloodPressure, pulse, haemContent  FROM donors inner join donation on donors.donorID=donation.donorID";
  $result=$db->query($donation) or die(mysqli_error());
  $nrows=$result->num_rows; // OOP
  if($nrows>0){
    $data = array();
    while($row = mysqli_fetch_assoc($result)){
      $data[] = $row;
    }
    echo json_encode($data);
  }
  ?>