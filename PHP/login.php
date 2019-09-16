<?php
$userN = "s1355485";
$passW = "s1355485";
$database = "d1355485";
$link = mysqli_connect("127.0.0.1", $userN, $passW, $database);

$username = $_REQUEST["username"];
$password = $_REQUEST["password"];

$query = mysqli_query($link, "select * from `User Table` where Username = '$username' and Password = '$password'");


if(mysqli_num_rows($query) > 0) echo "1";

else echo "0";

mysqli_close($link);

?>
