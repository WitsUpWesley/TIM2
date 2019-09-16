<?php
$userN = "s1355485";
$passW = "s1355485";
$database = "d1355485";
$link = mysqli_connect("127.0.0.1", $userN, $passW, $database);
$output = array();
$shopName=$_REQUEST["shopName"];
$shopDescription=$_REQUEST["shopDescription"];
$type=$_REQUEST["type"];
$owner=$_REQUEST["owner"];

/* Select queries return a resultset */
if($result = mysqli_query($link, "INSERT INTO `Shops`(`Shop Name`, `Shop Description`, `Type`, `Owner`, `Deleted`) VALU$$output[]=$row;
}
}
mysqli_close($link);
echo json_encode($output);
?>
