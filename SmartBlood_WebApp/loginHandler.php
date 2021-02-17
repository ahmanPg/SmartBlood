<?php
  
session_start();

if(isset($_POST["submit"])) {
   require_once('connection/config.php');
   $myusername =$db->real_escape_string($_POST['username']);
   $mypassword =$db->real_escape_string($_POST['password']); 
   $newpassword=sha1($mypassword);
   $sql = "SELECT * FROM users inner join roles on utype=roles.id WHERE userName = '$myusername' and passID = '$newpassword'";
   $result = $db->query($sql) or die(mysqli_error());
   $count = mysqli_num_rows($result);
   //detect error in quermy
   if (!$result){
      printf("Erro: %s\n", mysqli_error($db));
      exit();
   }
   // If result matched $myusername and $mypassword, table row must be 1 row
   if($count==1) {
      $row = mysqli_fetch_array($result);
      if ($row["status"] == 'active') {
         $_SESSION['login_user'] = $myusername;
         $_SESSION["user_role"]=$row["id"];
         header("location:home.php");
      }else{
         header("location:index.php?error=Your account hase been blocked!");
      }          
     // session_register("myusername");
    
   }else {
      header("location:index.php?error=Wrong Username/password & username=".$myusername);
   }

   $db->close();
}



?>