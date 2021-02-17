<?php
require_once '../connection/config.php';
$response = array();

if(isset($_GET['apicall'])){
    switch($_GET['apicall']){
    // If apicall is signup do this
        case 'signup': 
            // check if params are set correctly 
            if(isTheseParametersAvailable(array('username', 'email', 'password', 'firtst_name', 'last_name', 'phone', 'blood_type', 'weight', 'gender', 'birth_date', 'district', 'province', 'user_type'))){
                $email = $_POST['email'];   
                $username = $_POST['username'];

               // check if user already exist;
                $stmt = $db->prepare("SELECT userID FROM users WHERE userName = ? OR email = ?");  
                $stmt->bind_param("ss", $username, $email);  
                $stmt->execute();
                $stmt->store_result();  

                if($stmt->num_rows > 0){  
                    $response['error'] = true;  
                    $response['message'] = 'User already registered';  
                    $stmt->close();  
                }else{
                    //record all params
                    $password = sha1($_POST['password']);
                    $firtst_name = $_POST['firtst_name'];
                    $last_name = $_POST['last_name'];
                    $phone = $_POST['phone'];
                    $blood_type = $_POST['blood_type'];
                    $weight = $_POST['weight'];
                    $gender = $_POST['gender'];
                    $birth_date = $_POST['birth_date'];
                    $district = $_POST['district'];
                    $province = $_POST['province'];
                    $user_type = 2;
                    $question = $_POST['question'];
                    $answer = $_POST['answer'];
                    $status = 'active';
                    $date = date('Y-m-d H-i-s');
                    
                    $stmt = $db->prepare("INSERT INTO users (userID, userName, email, passID, utype, status, createdAt, updatedAt, secQuestion, secAnswer) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    $stmt->bind_param("sssssssss", $username, $email, $password, $user_type, $status, $date, $date, $question, $answer); 
                    if($stmt->execute()){
                        $stmt->close();
                        $stmt = $db->prepare("SELECT userID FROM users WHERE userName = ? OR email = ? limit 1");
                        $stmt->bind_param("ss", $username, $email);
                        //problem here!!
                        if ($stmt->execute()) {
                            echo "string";
                            $row = $stmt->fetch(PDO::FETCH_ASSOC);
                            $id = $row['userID'];

                        };
                        // check if any raw affected
                        $stmt->store_result();
                        if ($stmt->num_rows > 0) {
                            echo "string";
                            $stmt = $db->prepare("INSERT INTO donors (donorID, fname, lname, phone, bloodType, weight, gender, birthDate, district, province) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE donorID = :user_id");
                            $stmt->bind_param("sssssssss", $firtst_name, $last_name, $phone, $bloodType, $weight, $gender, $birth_date, $district, $province);    break;
                            $stmt->execute(['user_id' => $id]);

                        }
                       
                        $stmt = $db->prepare("SELECT userID, userName, email, utype FROM users WHERE username = ?");   
                        $stmt->bind_param("s",$username);  
                        $stmt->execute();  
                        $stmt->bind_result($id, $username, $email, $user_type);  
                        $stmt->fetch();  
               
                        $user = array(  
                            'id'=>$id,   
                            'username'=>$username,   
                            'email'=>$email,  
                            'utype'=>$user_type  
                        );  
               
                        $stmt->close();  
                        $response['error'] = false;   
                        $response['message'] = 'User registered successfully';   
                        $response['user'] = $user;   
                    }else{
                        echo "failed";
                        $response["error"] = true;
                        $response["message"] = 'Registration Failed!';
                    }  
                }  

            }else{  
                $response['error'] = true;   
                $response['message'] = 'required parameters are not available';   
            }  

        break;
        // If apicall is login do this
        case 'login':
            // check if params are set correctly 
            if(isTheseParametersAvailable(array('username', 'password'))){  
                $username = $_POST['username'];  
                $password = sha1($_POST['password']); 
                // echo $username;
                // break;
                $stmt = $db->prepare("SELECT userID, email, utype FROM users WHERE userName = ? AND passID = ?");  
                $stmt->bind_param("ss", $username, $password);  
                $stmt->execute();  
                $stmt->store_result();
                // Check if user exist  
                if($stmt->num_rows > 0){  
                    $stmt->bind_result($id, $email, $usertype);  
                    $stmt->fetch();
                    // store user details for response  
                    $user = array(  
                        'id'=>$id,   
                        'username'=>$username,   
                        'email'=>$email,  
                        'type'=>$usertype 
                    );  
                   
                    $response['error'] = false;   
                    $response['message'] = 'Login successfull';   
                    $response['user'] = $user;   
                }else{  
                    $response['error'] = true;   
                    $response['message'] = 'Invalid username or password';
                    $response['user'] = None;
                }

            }else{  
                $response['error'] = true;   
                $response['message'] = 'required parameters are not available. why??';   
            }    
        break;

        default:   
            $response['error'] = true;   
            $response['message'] = 'Invalid Operation Called';  
    }  
}else{  
    $response['error'] = true;   
    $response['message'] = 'Invalid API Call';  
}

echo json_encode($response);

function isTheseParametersAvailable($params){  
    foreach($params as $param){  
        if(!isset($_POST[$param])){  
            return fasle;   
      }  
    }  
    return true;   
}  
?>  