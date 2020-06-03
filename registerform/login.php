<?php
require "conn.php"

$email=$_POST["email"];
$password=$_POST["pass"];
$isValidEmail = filter_var($email, FILTER_VALIDATE_EMAIL);
if($conn){
if($isValidEmail === false){

echo "This Email is not valid";
}else{
$sqlCheckEmail = "SELECT * FROM `user_table` WHERE `email` LIKE '$email'";
$usernameQuery = mysqli_query($conn, $sqlCheckEmail);

if(mysqli_num_rows($usernameQuery)>0){
$sqlLogin = "SELECT * FROM `user_table` WHERE `email` LIKE '$email' AND `password`LIKE '$p assword'";
if(mysqli_num_rows($sqlLogin)>0){
echo "Login success";
}else{
echo "Wrong Password"
} 
}else{
echo "This Email is not registered"
} 
}
}
else{
echo "Connection Error";
}

?>