<?php
$userN = "s1355485";
$passW = "s1355485";
$database = "d1355485";
$link = mysqli_connect("127.0.0.1", $userN, $passW, $database);

$shopName = $_REQUEST["shopName"];
$productName = $_REQUEST["productName"];
$itemName = $_REQUEST["itemName"];
$numItemsNeeded = $_REQUEST["numItemsNeeded"];


$query = mysqli_query($link, "INSERT INTO `productTbl`(`ID`, `shopName`, `productName`, `itemName`, `numItemsNeeded`, `$
if($query){
  echo "1";
}
else{
  echo "0";
}
mysqli_close($link);

?>
