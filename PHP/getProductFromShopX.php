<?php
$userN = "s1355485";
$passW = "s1355485";
$database = "d1355485";
$link = mysqli_connect("127.0.0.1", $userN, $passW, $database);
$output = array();
$shopName=$_REQUEST["shopName"];

if($result = mysqli_query($link, "SELECT  `productName`, `itemName`, `numItemsNeeded`  FROM `productTbl` WHERE `shopNam$$output[]=$row;
}
}
mysqli_close($link);
echo json_encode($output);
?>
