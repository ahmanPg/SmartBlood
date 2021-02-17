<?php
session_start();
if(!isset($_SESSION["login_user"])){
    header("location:../smbb/index.php?error=hups!, you have to login first");
}
?>
<!doctype html>
<html class="no-js" lang="en">

<head><meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Donations Report</title>
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
    <!-- data-table CSS
        ============================================ -->
    <link rel="stylesheet" href="css/data-table/bootstrap-table.css">
    <link rel="stylesheet" href="css/data-table/bootstrap-editable.css">
    <!-- normalize CSS
        ============================================ -->
    <link rel="stylesheet" href="css/normalize.css">
    <!-- charts C3 CSS
        ============================================ -->
    <link rel="stylesheet" href="css/c3.min.css">
    <!-- forms CSS
        ============================================ -->
    <link rel="stylesheet" href="css/form/all-type-forms.css">
    <!-- switcher CSS
        ============================================ -->
    <link rel="stylesheet" href="css/switcher/color-switcher.css">
    <!-- style CSS
        ============================================ -->
    <link rel="stylesheet" href="style.css">
    <!-- responsive CSS
        ============================================ -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- modernizr JS
        ============================================ -->
    <script src="js/vendor/modernizr-2.8.3.min.js"></script>
    <!-- Color Css Files
        ============================================ -->
    
</head>

<body>
    <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
    <?php
   include('donation_top.php');
   ?>
    <!-- Mobile Menu end -->
    <!-- welcome Project, sale area start-->
        <!-- stockprice, feed area end-->
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

    <!-- Data table area Start-->
    <?php
  require_once("connection/config.php");
  $donation="SELECT donationNo, centerName, donatedDate, bloodPressure, haemContent, pulse, bloodType, concat(fname,' ',lname) as fullName FROM donation INNER JOIN donors ON donation.donorID=donors.donorID";
  $result=$db->query($donation) or die(mysqli_error($db));
  $nrows=$result->num_rows; // OOP
  //echo $nrows;
    ?>
    <div class="admin-dashone-data-table-area mg-b-40">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="sparkline8-list shadow-reset">
                        <div class="sparkline8-hd">
                            <div class="main-sparkline8-hd">
                                <h1>Donation List</h1>
                                <div class="sparkline8-outline-icon">
                                    <span class="sparkline8-collapse-link"><i class="fa fa-chevron-up"></i></span>
                                    <span><i class="fa fa-wrench"></i></span>
                                    <span class="sparkline8-collapse-close"><i class="fa fa-times"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="sparkline8-graph">
                            <div class="datatable-dashv1-list custom-datatable-overright">
                                <div id="toolbar">
                                    <select class="form-control">
                                        <option value="">Export Basic</option>
                                        <option value="all">Export All</option>
                                        <option value="selected">Export Selected</option>
                                    </select>
                                </div>
                                <table id="table" data-toggle="table" data-pagination="true" data-search="true" data-show-columns="true" data-show-pagination-switch="true" data-show-refresh="true" data-key-events="true" data-show-toggle="true" data-resizable="true" data-cookie="true" data-cookie-id-table="saveId" data-show-export="true" data-click-to-select="true" data-toolbar="#toolbar">
                                    <thead>
                                        <tr>
                                            <th data-field="state" data-checkbox="true"></th>
                                            <th data-field="donationNumber" data-editable="true">DonationNo</th>
                                            <th data-field="donorName" data-editable="true">Donor Name</th>
                                            <th data-field="group" data-editable="true">Group</th>
                                            <th data-field="centerName" data-editable="true">Cneter Name</th>
                                            <th data-field="date" data-editable="true">Date</th>
                                            <th data-field="bp" data-editable="true">Bp</th>
                                            <th data-field="hb" data-editable="true">Hb Content</th>
                                            <th data-field="pulse" data-editable="true">Pulse</th>
                                            <th data-field="edit">Edit</th>
                                            <th data-field="delate">Delete</th>
                
                                        </tr>
                                    </thead>
                                    <tbody id="data">
                                    
                                        <?php 

                                            if($nrows>0){
                                                /*while($r=$query->fetch_array()){
                                                    //OOP
                                                }*/
                                                    while($r=mysqli_fetch_array($result)){
                                                        ?>
                                                        <tr>
                                                            <td></td>
                                                            <td><?php echo $r["donationNo"] ?></td>
                                                            <td><?php echo $r["fullName"] ?></td>
                                                            <td><?php echo $r["bloodType"] ?></td>
                                                            <td><?php echo $r["centerName"] ?></td>
                                                            <td><?php echo $r["donatedDate"] ?></td>
                                                            <td><?php echo $r["bloodPressure"] ?></td>
                                                            <td><?php echo $r["haemContent"] ?></td>
                                                            <td><?php echo $r["pulse"] ?></td>
                                                            <td><a href="edit_donor.php?edit_id=<?php  echo $r["donorID"] ?>"><i class="fa fa-edit fa-lg"></a></td>
                                                            <td><a onClick="javascript: return confirm('DO You want to delete')" href="delete_donor.php?delete_id=<?php  echo $r["donorID"] ?>"><i class="fa fa-trash fa-lg"></i></a></td>
                                                        </tr>
                                                    <?php       
                                                    }       
                                            }else{
                                                ?>
                                                <tr><td colspan="6" align="center">No Record Found</td></tr>
                                                <?php
                                            }
                                            ?>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th data-field="state" data-checkbox="true"></th>
    
                                            <th data-field="donationNumber" data-editable="true">DonationNo</th>
                                            <th data-field="donorName" data-editable="true">Donor Name</th>
                                            <th data-field="group" data-editable="true">Group</th>
                                            <th data-field="centerName" data-editable="true">Cneter Name</th>
                                            <th data-field="date" data-editable="true">Date</th>
                                            <th data-field="bp" data-editable="true">Bp</th>
                                            <th data-field="hb" data-editable="true">Hb Content</th>
                                            <th data-field="pulse" data-editable="true">Pulse</th>
                                            <th data-field="edit">Edit</th>
                                            <th data-field="delate">Delete</th>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Data table area End-->
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
    <!-- Color Switcher -->
  
    <!-- Color Switcher end -->
    <!-- Chat Box Start-->

    <!-- Chat Box End-->
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