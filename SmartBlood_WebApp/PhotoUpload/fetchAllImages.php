<?php
    require_once('config.php');
    
    $sql = "SELECT profile FROM donors WHERE donorID = 1";
    
    $res = mysqli_query($db,$sql);
    
    $result = array();
    
    while($row = mysqli_fetch_array($res)){
        array_push($result,array('url'=>$row['profile']));
    }
    
    echo json_encode(array("result"=>$result));
    
    mysqli_close($db);