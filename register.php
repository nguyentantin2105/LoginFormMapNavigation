<?php
require "conn.php"

/*$username=$_POST["username"];
$email=$_POST["email"];
$password=$_POST["pass"];
$phone=$_POST["phone"];
$address = $_POST["address"];*/

$username="tin";
$email="tin@gmail.com";
$password="123456";
$phone="0328519310";
$address = "ktx khu b";

$isValidEmail = filter_var($email, FILTER_VALIDATE_EMAIL);
if($conn){
if(strlen($password)>40||strlen($password)<6){
echo "Password must be less than 40 and more than 6 characters";
}else if($isValidEmail === false){

echo "This Email is not valid";
}
else{
$sqlCheckUsername = "SELECT * FROM `user_table` WHERE `username` LIKE '$username'";
$usernameQuery = mysqli_query($conn, $sqlCheckUsername); 

$sqlCheckEmail = "SELECT * FROM `user_table` WHERE `email` LIKE '$email'";
$usernameQuery = mysqli_query($conn, $sqlCheckEmail);

if(mysqli_num_rows($usernameQuery)>0){
echo "User name is already used, type another one";
}else if(mysqli_num_rows($usernameQuery)>0){
echo "This Email is Already registered, Type another Email";
}
else{
$sql_register = "INSERT INTO `user_table` (`username`,`email`,`password`,`phone`,`address`) VALUES ('$username','email','password','phone','address')";

if(mysqli_query($conn,$sql_register)){
echo "Successfully Registered";
}
else{echo "Failed to Register";}
}
}
}
else{
echo "Connection Error"; 
}

?>