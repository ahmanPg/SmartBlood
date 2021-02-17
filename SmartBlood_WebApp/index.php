
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>SmartBlood</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <!-- favicon
    ============================================ -->
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
    <!-- Google Fonts
    ============================================ -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i,800" rel="stylesheet">
    <!-- Bootstrap CSS
    ============================================ -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Bootstrap CSS
    ============================================ -->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!-- adminpro icon CSS
    ============================================ -->
    <link rel="stylesheet" href="css/adminpro-custon-icon.css">
    <!-- meanmenu icon CSS
    ============================================ -->
    <link rel="stylesheet" href="css/meanmenu.min.css">
    <!-- mCustomScrollbar CSS
    ============================================ -->
    <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
    <!-- animate CSS
    ============================================ -->
    <link rel="stylesheet" href="css/animate.css">
    <!-- modals CSS
    ============================================ -->
    <link rel="stylesheet" href="css/modals.css">
    <!-- normalize CSS
    ============================================ -->
    <link rel="stylesheet" href="css/normalize.css">
    <!-- forms CSS
    ============================================ -->
    <link rel="stylesheet" href="css/form/all-type-forms.css">
    <!-- style CSS
    ============================================ -->
    <link rel="stylesheet" href="style.css">
    <!-- responsive CSS
    ============================================ -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- modernizr JS
    ============================================ -->
    <script src="js/vendor/modernizr-2.8.3.min.js"></script>
</head>
<body>
	<?php
		include('dashboard_top.php');
	?>
  <script type="text/javascript">
    function validateForm(){
      var formID = document.getElementById("loginForm");
      var username = loginForm.usernameID.value;
      var password = loginForm.passwordID.value;
      
      if(username==""){
        alert("Please Insert Username");
        formID.usernameID.style.backgroundColor="red";
        return false;
      }else if(password==""){
      	formID.passwordID.style.backgroundColor="red";
        alert("Please Insert Password");
        return false;
      }else if(password.length<4 || password.length>12){
        return false;
        alert("Password out of range!");
      }else{
        return true;
      }
          
    }
  </script>
<!-- Horizontal Login Form start -->
			
	<div class="col-lg-12" >
                          <div class="sparkline9-graph" style="float: right";>
                                  <div class="row">
                                      <div class="col-lg-12">
                                          <div class="sparkline9-graph">
                                            <?php
              if(isset($_GET["error"])){
                ?>
                <p style="color: red; text-align: center"><?php  echo $_GET["error"] ?></p>
                <?php
              }
              ?>
              <?php 
              if(isset($_GET["status"])){
                ?>
                <p style="color: green; text-align: center"><?php  echo $_GET["status"] ?></p>
                <?php
              }?>
                                              <h3>Already a user? Sign in here!</h3>
                                              <form id="loginForm" onSubmit="return validateForm()" action="loginHandler.php"  method="post" class="form-inline" role="form" >
                                                  <div class="form-group-inner">
                                                      <div class="row">
                                                          <div class="col-lg-4">
                                                              <label class="login2">Username</label>
                                                          </div>
                                                          <div class="col-lg-8">
                                                              <input id="usernameID" type="text" class="form-control" placeholder="Enter username" name="username" />
                                                          </div>
                                                      </div>
                                                </div>
                                                <div class="form-group-inner">
                                                    <div class="row">
                                                        <div class="col-lg-4">
                                                            <label class="login2">Password</label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <input id="passwordID" type="password" class="form-control" placeholder="password" name="password"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="login-btn-inner">
                                                    <div class="row">
                                                        <div class="col-lg-8">
                                                              <div class="i-checks">
                                                                <label>
                                                                  <input type="checkbox"><i></i> Remember me </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-4"></div>
                                                        <div class="col-lg-8">
                                                            <div class="login-horizental">
                                                                <button class="btn btn-sm btn-primary login-submit-cs" name="submit" type="submit">Sign In</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-4"></div>
                                                        <div class="col-lg-8">
                                                            <div class="login-horizental">
                                                               <a class="btn btn-sm btn-link " href="fogot_password.php" role="button">Forgot Password?</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <!-- Right Dash start -->
                                    	<div style="padding-top: 50px;">
		                                    <div class="form-group-inner">
		                                    	<h3>Where to donate <i style="color: green;" class="fas fa-map-marker-alt fa-sm"></i></h3>
		                                        <div class="row">
		                                            <div class="col-lg-4">
		                                                <label class="login2">Your Location</label>
		                                            </div>
		                                            <div class="col-lg-8">
		                                                <input type="text"  class="form-control" placeholder="City/District">
		                                            </div>
		                                            <div class="row">
                                                        <div class="col-lg-4"></div>
                                                        <div class="col-lg-8">
                                                            <div class="login-horizental">
                                                                <button type="submit" class="btn  btn-default" href="#"><i style="color: #b30000;" class="fas  fa-search"></i> Search</button>
                                                            </div>
                                                        </div>
                                                    </div>
		                                        </div>
		                                    </div>
		                                    <div class="form-group-inner" style="padding-top: 20px; background-color: #ffe6e6; border-radius: 5px;">
		                                    	<h3>Become a blood donor <i style="color: green;" class="fas fa-check-square fa-sm"></i></h3>
		                                        <div class="row">
		                                           <p>If you are completely new to blood donation</p>
		                                            <div class="row">
                                                        <div class="col-lg-4"></div>
                                                        <div class="col-lg-8">
                                                            <div class="login-horizental">
                                                               <a class="btn  btn-success btn-link btn-lg" href="#" role="button">Register</a>
                                                            </div>
                                                        </div>
                                                    </div>
		                                        </div>
		                                    </div>
		                                    <div class="login-btn-inner" style="padding-top: 20px; background-color:  #e6ffe6; border-radius: 5px;">
		                                    	<h3>Already a donor? <i style="color: red;"class="fas fa-user-plus fa-sm"></i></h3>
		                                        <div class="row">
		                                        	<p>Sign up for an online account to manage appointments</p>
		                                            <div class="row">
		                                            	<div class="col-lg-4"></div>
                                                        <div class="col-lg-8">
                                                            <div class="login-horizental">
                                                               <a class="btn  btn-success btn-link btn-lg" href="registration.php" role="button">Create an account</a>
                                                            </div>
                                                        </div>
                                                    </div>
		                                        </div>
		                                    </div>
		                                    <div class="login-btn-inner" style="padding-top: 20px;">
		                                    	<h3>Who can give blood? <i style="color: #cc0000;"class="fas fa-question fa-sm"></i></h3>
		                                        <div class="row">
		                                        	<p>Most people can give blood, if they are fit and healthy</p>
		                                            <div class="row">
		                                            	<div class="col-lg-4"></div>
                                                    <div class="col-lg-8">
                                                      <div class="login-horizental">
                                                        <a style="color: #cc0000;" class="btn-link btn btn-lg" href="#" role="button"><small>>>></small>Check you can give<small><<<</small></a>
                                                      </div>
                                                    </div>
                                                </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
                            	</div>
                        	</div>
                           
    <div class="container col-lg-8">
      <form class="form-group" action="search1.php" method="post" role="form">
      <div class="form-inline">
        <select class="form-control" name="country">
          <option value="selected">Tanzania</option>
          <option>Kenya</option>
          <option>Sudan</option>
          <option>Somalia</option>
          <option>Uganda</option>
        </select>
        <select class="form-control" id="sel1">
          <option value="selected">-City/District-</option>
          <option>Zanzibar City</option>
          <option>Noth Pemba</option>
        </select>
        <select class="form-control" name="province">
          <option value="selected">-Province-</option>
          <option>Bububu</option>
          <option>K.Samaki</option>
          <option>Shangani</option>
        </select>
        <select class="form-control" name="group">
          <option value="selected">-Blood Group-</option>
          <option>AB+</option><option>AB-</option>
          <option>O+</option><option>O-</option>
          <option>A+</option><option>A-</option>
          <option>B+</option><option>B-</option>
          
        </select>
        <button type="submit" name="search" class="btn btn-default" style="margin-left: 20px;"><i class="fas fa-search " style="color: #b30000;"></i> Find a donor</button>
      </div>  
    </form> 

      
        <div class="col-lg-12" style="">
    
          <div class="container-fluid">
            <img class="img-fluid " alt="responsiveimage" src="image/donor4.jpg" width="600px" height="300px">  
          </div>
        <p class="lead">
        
        ZBB WELCOMES YOU to the Blood Bank database in our website. if you're a donor, We appriciete you
        signing up online as a Donor. If you need blood we re happy to serve you.
        </p>
        <p >
        While the Organisers have taken all steps nto obtain accurate and up-to-date information of potential blood donors,
        the Organisers and Blood Bank Computers do not guarantee accuracy of the information contained herein or the suitability of the persons listed as any liability for direct or concequential damage to any person using this blood donor listincluding loss of life or damage due to infection of any nature arising out of blood transfusion from person whose name
        </p>  
        <div class="row">
            <p class="display-4" style="color: #cc0000;">News, features and uppdates</p>
            <div style="padding-left: ;" class="col-md-6 mb-4">
              <a href="">
                  <img class="img-fluid" alt="responsiveimage" src="image/newdonor.jpg" width="400px" height="200px">
                  <h4>Are you a new blood donor?</h4>   
              </a>
              <p>Sign up today and be one of our first 2019 donors. More new donor slots are available in the new year.</p>
              <a href="">
                <img class="img-fluid" alt="responsiveimage" src="image/donor4.jpg" width="400px" height="200px">
                <h4>Last minute appointments</h4> 
              </a>
              <p>Sign in now to see the latest appointment availability in your area.</p>
          <a href="">
            <img class="img-fluid" alt="responsiveimage" src="image/1.jpg" width="400px" height="200px">
            <h4>Can't find an appointment?</h4> 
          </a>
          <p>
          Thank you for trying. Please book for a future date and read more about how we plan your donations.
        </p>
          <a href="">
            <img class="img-fluid" alt="responsiveimage" src="image/bg1.jpg" width="400px" height="200px">
            <h4>Types of donation</h4>
          </a>
          <p>
          Learn about the different types of blood donation.
        </p>
        </div>
        <div class="col-md-6 mb-4">
          <a href="">
            <img class="img-fluid" alt="responsiveimage" src="image/donor.jpg" width="400px" height="200px">
            <h4>Unsure whether you can donate?</h4> 
          </a>
          <p>
          Use our Health, Eligibility and Travel tool to confirm that you're ok to give blood.
        </p>
          <a href="">
            <img class="img-fluid" alt="responsiveimage" src="image/donor.jpg" width="400px" height="200px">
            <h4>Get the Smart Blood Bank App</h4> 
          </a>
          <p>
          If you already have an online account why not download our Apple or Android app to book and manage your appointments.
        </p>
          
          <a href="">
            <img class="img-fluid" alt="responsiveimage" src="image/contact.jpg" width="400px" height="200px">
            <h4>Contact us</h4> 
          </a>
          <p>
          Have a question? Want to leave feedback or share your blood donation story with us? Contact us here or call +255774337598.
        </p>
        </div>
        
    </div>
    <div class="panel-group">
       <div class="panel panel-default">
          <div class="panel-heading">
              <h4 class="panel-title">
              <a style="color: #cc0000;" data-toggle="collapse" href="#collapse1">Anything in your mind? <i style="color: #cc0000;" class="fas fa-arrow-down"></i> </a>
              </h4>
          </div>
          <div id="collapse1" class="panel-collapse collapse">
              <ul class="list-group">
                <div class="form-group">
                <label for="comment">Respond to us:</label>
              <textarea class="form-control" rows="5" name="comment" placeholder="Type your comment here..." id="comment"></textarea>
              <button type="submit" class="button btn- btn-sm"><i class="fas fa-comment" style="color: #b30000;"></i> Post</button>
            </div>
         
            </ul>
              <div class="panel-footer">We are the only one who can see this post.</div>
          </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  
         
<!-- Horizontal Login Form end -->
	
	
	<footer class="col-lg-8">
  		<div class="row">
    		<div class="col">
    			<p class="text-center">
    				Copyright &copy 2018- Smart Blood Bank All Right Reserved | <a href="#">Privacy</a> | <a href="#">Terms of use</a>
    			</p>
    			<p class="text-center">You can call us on: +255774337598</p>
      			<p class="text-center">Design by <a href="#">AhmanPG</a></p>
    		</div>
  	</div>
	</footer>
	<!-- jquery
        ============================================ -->
    <script src="js/vendor/jquery-1.11.3.min.js"></script>
    <!-- bootstrap JS
        ============================================ -->
    <script src="js/bootstrap.min.js"></script>
    <!-- meanmenu JS
        ============================================ -->
    <script src="js/jquery.meanmenu.js"></script>
    <!-- mCustomScrollbar JS
        ============================================ -->
    <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
    <!-- sticky JS
        ============================================ -->
    <script src="js/jquery.sticky.js"></script>
    <!-- scrollUp JS
        ============================================ -->
    <script src="js/jquery.scrollUp.min.js"></script>
    <!-- counterup JS
        ============================================ -->
    <script src="js/counterup/jquery.counterup.min.js"></script>
    <script src="js/counterup/waypoints.min.js"></script>
    <script src="js/counterup/counterup-active.js"></script>
    <!-- peity JS
        ============================================ -->
    <script src="js/peity/jquery.peity.min.js"></script>
    <script src="js/peity/peity-active.js"></script>
    <!-- sparkline JS
        ============================================ -->
    <script src="js/sparkline/jquery.sparkline.min.js"></script>
    <script src="js/sparkline/sparkline-active.js"></script>
    <!-- flot JS
        ============================================ -->
    <script src="js/flot/jquery.flot.js"></script>
    <script src="js/flot/jquery.flot.tooltip.min.js"></script>
    <script src="js/flot/jquery.flot.spline.js"></script>
    <script src="js/flot/jquery.flot.resize.js"></script>
    <script src="js/flot/jquery.flot.pie.js"></script>
    <script src="js/flot/Chart.min.js"></script>
    <script src="js/flot/flot-active.js"></script>
    <!-- map JS
        ============================================ -->
    <script src="js/map/raphael.min.js"></script>
    <script src="js/map/jquery.mapael.js"></script>
    <script src="js/map/france_departments.js"></script>
    <script src="js/map/world_countries.js"></script>
    <script src="js/map/usa_states.js"></script>
    <script src="js/map/map-active.js"></script>
    <!-- data table JS
        ============================================ -->
    <script src="js/data-table/bootstrap-table.js"></script>
    <script src="js/data-table/tableExport.js"></script>
    <script src="js/data-table/data-table-active.js"></script>
    <script src="js/data-table/bootstrap-table-editable.js"></script>
    <script src="js/data-table/bootstrap-editable.js"></script>
    <script src="js/data-table/bootstrap-table-resizable.js"></script>
    <script src="js/data-table/colResizable-1.5.source.js"></script>
    <script src="js/data-table/bootstrap-table-export.js"></script>
    <!-- switcher JS
        ============================================ -->
    <script src="js/switcher/styleswitch.js"></script>
    <script src="js/switcher/switch-active.js"></script>
    <!-- main JS
        ============================================ -->
    <script src="js/main.js"></script>





	
</body>
</html>