<?php
 
    if($_SERVER['REQUEST_METHOD']=='POST'){
        
        $image = $_POST['image'];
        
        require_once('config.php');
        
        $sql ="SELECT fname, donorID FROM donors WHERE donorID=1";
        
        $res = mysqli_query($db,$sql);
        
        $id = 0;
        
        while($row = mysqli_fetch_array($res)){
                $id = $row['donorID'];
        }
        
        $path = "uploads/$id.png";
        
        $actualpath = "html/SmartBlood/PhotoUpload/$path";
        
        $sql = "UPDATE donors SET profile = '$actualpath' WHERE donorID = 1";
        if(mysqli_query($db,$sql)){
            file_put_contents($path,base64_decode($image));
            echo "Image uploaded successfully";
        }
        
        mysqli_close($db);
    }else{
        echo "Error";
    }
    ?>