<?php
$userN = "s1355485";
$passW = "s1355485";
$database = "d1355485";
$link = mysqli_connect("127.0.0.1", $userN, $passW, $database);

$shopName = $_REQUEST["shopName"];
$itemName = $_REQUEST["itemName"];
$itemDescription = $_REQUEST["Description"];
$itemQuantity = $_REQUEST["itemQuantity"];


$query = mysqli_query($link, "INSERT INTO `itemTbl`(`ID`, `shopName`, `itemName`, `itemDescription`, `itemQuantity`, `d$
if($query){
  echo "1";
}
else{
  echo "0";
}
mysqli_close($link);

?>
