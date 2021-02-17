<?php
if(isset($_POST["username"])) {
   require_once('../connection/config.php');
   $myusername =$db->real_escape_string($_POST['username']);
   $mypassword =$db->real_escape_string($_POST['password']); 
   $newpassword=sha1($mypassword);
   $result = $db->query("SELECT * FROM users inner join roles on utype=roles.id WHERE userName = '$myusername' and passID = '$newpassword'") or die(mysqli_error());
   $count = mysqli_num_rows($result);
   //detect error in quermy
   if (!$result){
      printf("Erro: %s\n", mysqli_error($con));
      exit();
   }
   // If result matched $myusername and $mypassword, table row must be 1 row
   if($count==1) {
      $row = mysqli_fetch_array($result);
      if ($row["status"] == 'active') {
         echo "success";
      }else{
         echo "blocked";
      }          
     // session_register("myusername");
    
   }else {
      echo "failed";
   }

   $db->close();
}



?>