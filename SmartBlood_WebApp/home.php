
<?php
include("loginHandler.php");
if ($_SESSION["user_role"]==1) {
	header("location:dashboard.php");
}elseif($_SESSION["user_role"]==2){
	include('donor.php');
}


?>