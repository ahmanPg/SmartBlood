 <?php 
    include('header_top.php');
?>
    <!-- Main Menu area start-->
    <div class="main-menu-area mg-tb-40" style="background-color: #cc0000;">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <ul class="nav nav-tabs custom-menu-wrap">
                        <li>
                            <a href="dashboard.php"><i class="fa fa-home fa-lg" ></i> Home</a>
                        </li>
                        <li  >
                            <a data-toggle="tab" href="#donations"><i class="fa fa-heart fa-lg"></i> Donations</a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#centers"><i class="fa fa-hospital fa-lg"></i> Centers</a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#broadcast"><i class="fa fa-bullhorn fa-lg"></i> Broadcast</a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#appointments"><i class="fa fa-bell fa-lg"></i>  Appoinments</a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#donors"><i class="fas fa-users fa-lg"></i> Donors</a>
                        </li>
                        <li class="active">
                            <a data-toggle="tab" href="#reports" aria-expanded="false"><i class="fas fa-file-medical-alt fa-lg"></i> Reports</a>
                        </li>
                    </ul>
                    <div class="tab-content custom-menu-content">
                        <div id="donations" class="tab-pane tab-custon-menu-bg animated flipInX">
                            <ul class="main-menu-dropdown">
                                <li><a href="view_donations.php">View Donations</a>
                                </li>
                                <li><a href="record_donation.php">Record Donation</a>
                                </li>
                            </ul>
                        </div>
                        <div id="centers" class="tab-pane tab-custon-menu-bg animated flipInX">
                            <ul class="main-menu-dropdown">
                                <li><a href="view_center.php">View Center</a>
                                </li>
                                <li><a href="register_center.php">Regitser Center</a>
                                </li>
                                <li><a href="view_doctor.php">View Doctors</a>
                                </li>
                                <li><a href="register_doctor.php">Register Doctors</a>
                                </li>
                            </ul>
                        </div>
                        <div id="broadcast" class="tab-pane tab-custon-menu-bg animated flipInX">
                            <ul class="main-menu-dropdown">
                                <li><a href="google-map.html">Start Broadcasting</a>
                                </li>
                                <li><a href="data-maps.html">View Broadscasts</a>
                                </li>
                                <li><a href="pdf-viewer.html">Share</a>
                                </li>
                            </ul>
                        </div>
                        <div id="appointments" class="tab-pane tab-custon-menu-bg animated flipInX">
                            <ul class="main-menu-dropdown">
                                <li><a href="notifications.html">Manage Appoiments</a>
                                </li>
                                <li><a href="alerts.html">View Appoiments</a>
                                </li>
                            </ul>
                        </div>
                        <div id="donors" class="tab-pane tab-custon-menu-bg animated flipInX">
                            <ul class="main-menu-dropdown">
                                <li><a href="view_donors.php">View Donors</a>
                                </li>
                                <li><a href="register_donor.php">Register Donors</a>
                                </li>
                            </ul>
                        </div>
                        <div id="reports" class="tab-pane in active tab-custon-menu-bg animated flipInX">
                            <ul class="main-menu-dropdown">
                                <li><a href="donationReport.php">Donation Report</a>
                                </li>
                                <li><a href="centerReport.php">Center Report</a>
                                </li>
                                <li><a href="static.php">Statics</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Main Menu area End-->
    <!-- Mobile Menu start -->
    <div class="mobile-menu-area">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="mobile-menu">
                        <nav id="dropdown">
                            <ul class="mobile-menu-nav">
                                <li><a data-toggle="collapse" data-target="#Charts" href="#">Home <span class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
                                    <ul class="collapse dropdown-header-top">
                                        <li><a href="dashboard.html">Dashboard v.1</a>
                                        </li>
                                        <li><a href="dashboard-2.html">Dashboard v.2</a>
                                        </li>
                                        <li><a href="analytics.html">Analytics</a>
                                        </li>
                                        <li><a href="widgets.html">Widgets</a>
                                        </li>
                                    </ul>
                                </li>
                                <li><a data-toggle="collapse" data-target="#demo" href="#">Mailbox <span class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
                                    <ul id="demo" class="collapse dropdown-header-top">
                                        <li><a href="inbox.html">Inbox</a>
                                        </li>
                                        <li><a href="view-mail.html">View Mail</a>
                                        </li>
                                        <li><a href="compose-mail.html">Compose Mail</a>
                                        </li>
                                    </ul>
                                </li>
                                <li><a data-toggle="collapse" data-target="#others" href="#">Miscellaneous <span class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
                                    <ul id="others" class="collapse dropdown-header-top">
                                        <li><a href="profile.html">Profile</a>
                                        </li>
                                        <li><a href="contact-client.html">Contact Client</a>
                                        </li>
                                        <li><a href="contact-client-v.1.html">Contact Client v.1</a>
                                        </li>
                                        <li><a href="project-list.html">Project List</a>
                                        </li>
                                        <li><a href="project-details.html">Project Details</a>
                                        </li>
                                    </ul>
                                </li>
                                <li><a data-toggle="collapse" data-target="#Miscellaneousmob" href="#">Interface <span class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
                                    <ul id="Miscellaneousmob" class="collapse dropdown-header-top">
                                        <li><a href="google-map.html">Google Map</a>
                                        </li>
                                        <li><a href="data-maps.html">Data Maps</a>
                                        </li>
                                        <li><a href="pdf-viewer.html">Pdf Viewer</a>
                                        </li>
                                        <li><a href="x-editable.html">X-Editable</a>
                                        </li>
                                        <li><a href="code-editor.html">Code Editor</a>
                                        </li>
                                        <li><a href="tree-view.html">Tree View</a>
                                        </li>
                                        <li><a href="preloader.html">Preloader</a>
                                        </li>
                                        <li><a href="images-cropper.html">Images Cropper</a>
                                        </li>
                                    </ul>
                                </li>
                                <li><a data-toggle="collapse" data-target="#Chartsmob" href="#">Charts <span class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
                                    <ul id="Chartsmob" class="collapse dropdown-header-top">
                                        <li><a href="bar-charts.html">Bar Charts</a>
                                        </li>
                                        <li><a href="line-charts.html">Line Charts</a>
                                        </li>
                                        <li><a href="area-charts.html">Area Charts</a>
                                        </li>
                                        <li><a href="rounded-chart.html">Rounded Charts</a>
                                        </li>
                                        <li><a href="c3.html">C3 Charts</a>
                                        </li>
                                        <li><a href="sparkline.html">Sparkline Charts</a>
                                        </li>
                                        <li><a href="peity.html">Peity Charts</a>
                                        </li>
                                    </ul>
                                </li>
                                <li><a data-toggle="collapse" data-target="#Tablesmob" href="#">Tables <span class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
                                    <ul id="Tablesmob" class="collapse dropdown-header-top">
                                        <li><a href="static-table.html">Static Table</a>
                                        </li>
                                        <li><a href="data-table.html">Data Table</a>
                                        </li>
                                    </ul>
                                </li>
                                <li><a data-toggle="collapse" data-target="#Tablesmob" href="#">Forms <span class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
                                    <ul id="Tablesmob" class="collapse dropdown-header-top">
                                        <li><a href="basic-form-element.html">Basic Form Elements</a>
                                        </li>
                                        <li><a href="advance-form-element.html">Advanced Form Elements</a>
                                        </li>
                                        <li><a href="password-meter.html">Password Meter</a>
                                        </li>
                                        <li><a href="multi-upload.html">Multi Upload</a>
                                        </li>
                                        <li><a href="tinymc.html">Text Editor</a>
                                        </li>
                                        <li><a href="dual-list-box.html">Dual List Box</a>
                                        </li>
                                    </ul>
                                </li>
                                <li><a data-toggle="collapse" data-target="#Appviewsmob" href="#">App views <span class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
                                    <ul id="Appviewsmob" class="collapse dropdown-header-top">
                                        <li><a href="basic-form-element.html">Basic Form Elements</a>
                                        </li>
                                        <li><a href="advance-form-element.html">Advanced Form Elements</a>
                                        </li>
                                        <li><a href="password-meter.html">Password Meter</a>
                                        </li>
                                        <li><a href="multi-upload.html">Multi Upload</a>
                                        </li>
                                        <li><a href="tinymc.html">Text Editor</a>
                                        </li>
                                        <li><a href="dual-list-box.html">Dual List Box</a>
                                        </li>
                                    </ul>
                                </li>
                                <li><a data-toggle="collapse" data-target="#Pagemob" href="#">Pages <span class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
                                    <ul id="Pagemob" class="collapse dropdown-header-top">
                                        <li><a href="login.html">Login</a>
                                        </li>
                                        <li><a href="register.html">Register</a>
                                        </li>
                                        <li><a href="captcha.html">Captcha</a>
                                        </li>
                                        <li><a href="checkout.html">Checkout</a>
                                        </li>
                                        <li><a href="contact.html">Contacts</a>
                                        </li>
                                        <li><a href="review.html">Review</a>
                                        </li>
                                        <li><a href="order.html">Order</a>
                                        </li>
                                        <li><a href="comment.html">Comment</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Mobile Menu end -->