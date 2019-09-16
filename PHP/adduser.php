<?php
$userN = "s1355485";
$passW = "s1355485";
$database = "d1355485";
$link = mysqli_connect("127.0.0.1", $userN, $passW, $database);

$username = $_REQUEST["username"];
$password = $_REQUEST["password"];

$query = mysqli_query($link, "insert into `User Table`(Username,Password) values('$username', '$password')");

if($query){
  echo "1";
}
else{
  echo "0";
}
mysqli_close($link);

?>
