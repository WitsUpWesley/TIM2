<?php
$userN = "s1355485";
$passW = "s1355485";
$database = "d1355485";
$link = mysqli_connect("127.0.0.1", $userN, $passW, $database);
$output = array();$owner=$_REQUEST["owner"];

if($result = mysqli_query($link, "SELECT  `Shop Name`, `Shop Description`, `Type`, `Owner`  FROM `Shops` WHERE `Owner`=$$output[]=$row;
}
}
mysqli_close($link);
echo json_encode($output);
?>
