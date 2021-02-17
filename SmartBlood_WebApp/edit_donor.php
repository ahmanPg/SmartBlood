<?php
    include('session.php');
?>
<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Edit Donor</title>
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
    <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
    <?php
    include('donor_top.php');

    require_once("connection/config.php");
    if(isset($_GET["edit_id"])){
        $edit_id=$db->real_escape_string($_GET["edit_id"]);
        
        $query=$db->query("SELECT * FROM users INNER JOIN donors ON users.userID=donors.donorID WHERE donorID='$edit_id'") or die($db->error);
        $row=mysqli_fetch_array($query);
}
   ?>
    <!-- Mobile Menu start -->

    <!-- Mobile Menu end -->             
            <div class="row">
                <div class="col-lg-12">
                    <div class="sparkline12-list shadow-reset mg-t-30">
                    <?php
                        if(isset($_GET["error"])){
                    ?>
                        <p style="color: red; text-align: center"><?php  echo $_GET["error"] ?></p>
                    <?php
                        }elseif(isset($_GET["status"])){
                    ?>
                        <p style="color: green; text-align: center"><?php  echo $_GET["status"] ?></p>
                    <?php
                        }?>
                        <div class="sparkline12-hd">
                            <div class="main-sparkline12-hd">
                                <h1>Edit Donor</h1>
                                <div class="sparkline12-outline-icon">
                                    <span class="sparkline12-collapse-link"><i class="fa fa-chevron-up"></i></span>
                                    <span><i class="fa fa-wrench"></i></span>
                                    <span class="sparkline12-collapse-close"><i class="fa fa-times"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="sparkline12-graph">
                            <div class="basic-login-form-ad">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="all-form-element-inner">
                                            <!-- Donation Forn start -->
                                            <form action="save_edit_donor.php" method="post">
                                                <div class="form-group-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <label class="login2 pull-right pull-right-pro">Donor ID</label>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <input type="text" class="form-control" name="donorID" value="<?php echo $row['donorID'];?>" readonly />
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <label class="login2 pull-right pull-right-pro">Donor's Number</label>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <input type="text" class="form-control" name="donorNo" value="<?php echo $row['donorNo'];?>" />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <label class="login2 pull-right pull-right-pro">First Name</label>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <input type="text" class="form-control" name="fname" value="<?php echo $row['fname'];?>" />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <label class="login2 pull-right pull-right-pro">Last Name</label>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <input type="text" class="form-control" name="lname" value="<?php echo $row['lname'];?>"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <label class="login2 pull-right pull-right-pro">Tel</label>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <input type="text" class="form-control" name="tel" value="<?php echo $row['phone'];?>" />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <label class="login2 pull-right pull-right-pro">Date Of Birth</label>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <input type="date" class="form-control" name="dob" value="<?php echo $row['birthDate'];?>"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <label class="login2 pull-right pull-right-pro">Weight (Kg)</label>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <input type="text" class="form-control" name="mass" value="<?php echo $row['weight'];?>" />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <label class="login2 pull-right pull-right-pro">District</label>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <input type="text" class="form-control" name="district" value="<?php echo $row['district'];?>" />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <label class="login2 pull-right pull-right-pro">Province</label>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <input type="text" class="form-control" name="province" value="<?php echo $row['province'];?>" />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <label class="login2 pull-right pull-right-pro">Gender</label>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <div class="form-select-list">
                                                                <select class="form-control custom-select-value" name="sex">
                                                                     <option <?php if($row["gender"]=='male') { ?> selected <?php } ?> value="male">Male</option>
                                                                      <option <?php if($row["gender"]=='female') { ?> selected <?php } ?> value="female">Female</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <label class="login2 pull-right pull-right-pro">Blood Group</label>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <div class="form-select-list">
                                                                <select class="form-control custom-select-value" name="group">
                                                                    <option <?php if($row["bloodType"]=='AB+') { ?> selected <?php } ?> value="AB+">AB+</option>
                                                                    <option <?php if($row["bloodType"]=='AB-') { ?> selected <?php } ?> value="AB-">AB-</option>
                                                                    <option <?php if($row["bloodType"]=='A+') { ?> selected <?php } ?> value="A+">A+</option>
                                                                    <option <?php if($row["bloodType"]=='A-') { ?> selected <?php } ?> value="A-">A-</option>
                                                                    <option <?php if($row["bloodType"]=='B+') { ?> selected <?php } ?> value="B+">B+</option>
                                                                    <option <?php if($row["bloodType"]=='B-') { ?> selected <?php } ?> value="B-">B-</option>
                                                                    <option <?php if($row["bloodType"]=='O+') { ?> selected <?php } ?> value="O+">O+</option>
                                                                    <option <?php if($row["bloodType"]=='O-') { ?> selected <?php } ?> value="O-">O-</option>

                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <label class="login2 pull-right pull-right-pro">Status</label>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <div class="form-select-list">
                                                                <select class="form-control custom-select-value" name="status">
                                                                    <option <?php if($row["status"]=='active') { ?> selected <?php } ?> value="active">Active</option>
                                                                    <option <?php if($row["status"]=='blocked') { ?> selected <?php } ?> value="blocked">Blocked</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group-inner">
                                                    
                                                        <div class="row">
                                                            <div class="col-lg-3"></div>
                                                            <div class="col-lg-9">
                                                                <div class="login-horizental cancel-wp pull-left">
                                                                    <button name="save" class="btn btn-sm btn-primary login-submit-cs" type="submit">Save Change</button>
                                                                    <button name="exit" class="btn btn-white" type="reset">Cancel</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                   

                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Basic Form End-->
    <!-- Footer Start-->
    <div class="footer-copyright-area">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="footer-copy-right">
                        <p>Copyright &#169; 2018 Colorlib All rights reserved. Template by <a href="https://colorlib.com">Colorlib</a>.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer End-->
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
    <!-- modal JS
    ============================================ -->
    <script src="js/modal-active.js"></script>
    <!-- icheck JS
    ============================================ -->
    <script src="js/icheck/icheck.min.js"></script>
    <script src="js/icheck/icheck-active.js"></script>
    <!-- main JS
    ============================================ -->
    <script src="js/main.js"></script>
</body>

</html>