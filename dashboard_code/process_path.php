<?php
session_start();

$color = array(
    "#00FF40",
    "#04FF3E",
    "#08FF3D",
    "#0CFF3C",
    "#10FF3B",
    "#14FF3A",
    "#18FF39",
    "#1CFF38",
    "#20FF37",
    "#24FF36",
    "#28FF35",
    "#2CFF34",
    "#30FF33",
    "#34FF32",
    "#38FF31",
    "#3CFF30",
    "#40FF2F",
    "#44FF2E",
    "#48FF2D",
    "#4CFF2C",
    "#50FF2B",
    "#55FF2A",
    "#59FF29",
    "#5DFF28",
    "#61FF27",
    "#65FF26",
    "#69FF25",
    "#6DFF24",
    "#71FF23",
    "#75FF22",
    "#79FF21",
    "#7DFF20",
    "#81FF1F",
    "#85FF1E",
    "#89FF1D",
    "#8DFF1C",
    "#91FF1B",
    "#95FF1A",
    "#99FF19",
    "#9DFF18",
    "#A1FF17",
    "#A5FF16",
    "#AAFF15",
    "#AEFF14",
    "#B2FF13",
    "#B6FF12",
    "#BAFF11",
    "#BEFF10",
    "#C2FF0F",
    "#C6FF0E",
    "#CAFF0D",
    "#CEFF0C",
    "#D2FF0B",
    "#D6FF0A",
    "#DAFF09",
    "#DEFF08",
    "#E2FF07",
    "#E6FF06",
    "#EAFF05",
    "#EEFF04",
    "#F2FF03",
    "#F6FF02",
    "#FAFF01",
    "#FFFF00",
    "#FFFF00",
    "#FFFA00",
    "#FFF600",
    "#FFF200",
    "#FFEE00",
    "#FFEA00",
    "#FFE600",
    "#FFE200",
    "#FFDE00",
    "#FFDA00",
    "#FFD600",
    "#FFD200",
    "#FFCE00",
    "#FFCA00",
    "#FFC600",
    "#FFC200",
    "#FFBE00",
    "#FFBA00",
    "#FFB600",
    "#FFB200",
    "#FFAE00",
    "#FFAA00",
    "#FFA500",
    "#FFA100",
    "#FF9D00",
    "#FF9900",
    "#FF9500",
    "#FF9100",
    "#FF8D00",
    "#FF8900",
    "#FF8500",
    "#FF8100",
    "#FF7D00",
    "#FF7900",
    "#FF7500",
    "#FF7100",
    "#FF6D00",
    "#FF6900",
    "#FF6500",
    "#FF6100",
    "#FF5D00",
    "#FF5900",
    "#FF5500",
    "#FF5000",
    "#FF4C00",
    "#FF4800",
    "#FF4400",
    "#FF4000",
    "#FF3C00",
    "#FF3800",
    "#FF3400",
    "#FF3000",
    "#FF2C00",
    "#FF2800",
    "#FF2400",
    "#FF2000",
    "#FF1C00",
    "#FF1800",
    "#FF1400",
    "#FF1000",
    "#FF0C00",
    "#FF0800",
    "#FF0400",
    "#FF0000");

if (!isset($_SESSION["TOTAL"]) ||
    !isset($_SESSION["A1"]) ||
    !isset($_SESSION["A2"]) ||
    !isset($_SESSION["B1"]) ||
    !isset($_SESSION["B2"])
) {
    $_SESSION["TOTAL"] = 0;
    $_SESSION["A1"] = 0;
    $_SESSION["A2"] = 0;
    $_SESSION["B1"] = 0;
    $_SESSION["B2"] = 0;
}

if ($_SESSION["A1"] > 128 ||
    $_SESSION["A2"] > 128 ||
    $_SESSION["B1"] > 128 ||
    $_SESSION["B2"] > 128
) {
    $_SESSION["TOTAL"] = 0;
    $_SESSION["A1"] = 0;
    $_SESSION["A2"] = 0;
    $_SESSION["B1"] = 0;
    $_SESSION["B2"] = 0;
}

$_SESSION["TOTAL"] = $_SESSION["TOTAL"] + 1;

$pilihan1 = "";
$pilihan2A = "";
$pilihan2B = "";
$pilihan3A = "";
$pilihan3B = "";
$pilihan4 = "";

$JsonPath[] = array('ID' => 'A0', 'x' => 100, 'y' => 160, 'speed' => 5000);

$arrPilihan1 = ['A1', 'A2'];
$pilihan1 = $arrPilihan1[mt_rand(0, count($arrPilihan1) - 1)];
//echo $pilihan1;

if ($pilihan1 == 'A1') {
    $JsonPath[] = array('ID' => 'A1', 'x' => 340, 'y' => 83, 'speed' => 500);
} else if ($pilihan1 == 'A2') {
    $JsonPath[] = array('ID' => 'A2', 'x' => 340, 'y' => 120, 'speed' => 500);
}


if ($pilihan1 == "A1") {
    $_SESSION["A1"] = $_SESSION["A1"] + 1;
    $pilihan2A = "B1";
    $_SESSION["B1"] = $_SESSION["B1"] + 1;
//    echo $pilihan2A;
    $JsonPath[] = array('ID' => 'B1', 'x' => 570, 'y' => 43, 'speed' => 500);

    $arrPilihan3A = ['EL', 'B2'];
    $pilihan3A = $arrPilihan3A[mt_rand(0, count($arrPilihan3A) - 1)];

    if ($pilihan3A == 'EL') {
//        echo $pilihan3A;
        $JsonPath[] = array('ID' => 'EL', 'x' => 300, 'y' => 20, 'speed' => 500);
    }

    if ($pilihan3A == 'B2') {
        $pilihan3B = 'B2';
        $_SESSION["B2"] = $_SESSION["B2"] + 1;
//        echo $pilihan3B;
        $JsonPath[] = array('ID' => 'B2', 'x' => 650, 'y' => 57, 'speed' => 500);
        $pilihna4 = 'ER';
//        echo $pilihna4;
        $JsonPath[] = array('ID' => 'ER', 'x' => 800, 'y' => 130, 'speed' => 500);
    }
} else if ($pilihan1 == "A2") {
    $_SESSION["A2"] = $_SESSION["A2"] + 1;
    $pilihan2A = "B2";
    $_SESSION["B2"] = $_SESSION["B2"] + 1;
//    echo $pilihan2A;
    $JsonPath[] = array('ID' => 'B2', 'x' => 650, 'y' => 57, 'speed' => 500);

    $arrPilihan3A = ['ER', 'B1'];
    $pilihan3A = $arrPilihan3A[mt_rand(0, count($arrPilihan3A) - 1)];

    if ($pilihan3A == 'ER') {
//        echo $pilihan3A;
        $JsonPath[] = array('ID' => 'ER', 'x' => 800, 'y' => 130, 'speed' => 500);
    }

    if ($pilihan3A == 'B1') {
        $pilihan3B = 'B1';
        $_SESSION["B1"] = $_SESSION["B1"] + 1;
//        echo $pilihan3B;
        $JsonPath[] = array('ID' => 'B1', 'x' => 570, 'y' => 43, 'speed' => 500);
        $pilihna4 = 'EL';
//        echo $pilihna4;
        $JsonPath[] = array('ID' => 'EL', 'x' => 300, 'y' => 20, 'speed' => 500);
    }
}

//echo $_SESSION["TOTAL"];
$TOTAL = $_SESSION["A1"] + $_SESSION["A2"] + $_SESSION["B1"] + $_SESSION["B2"];
//echo "A1:" . $color[ROUND($_SESSION["A1"] / $TOTAL * 100)];
//echo "B1:" . $color[ROUND($_SESSION["B1"] / $TOTAL * 100)];
//echo "A2:" . $color[ROUND($_SESSION["A2"] / $TOTAL * 100)];
//echo "B2:" . $color[ROUND($_SESSION["B2"] / $TOTAL * 100)];

//$JsonResult[] = array(
//    'A1' => $color[ROUND($_SESSION["A1"] / $TOTAL * 100)],
//    'B1' => $color[ROUND($_SESSION["B1"] / $TOTAL * 100)],
//    'A2' => $color[ROUND($_SESSION["A2"] / $TOTAL * 100)],
//    'B2' => $color[ROUND($_SESSION["B2"] / $TOTAL * 100)]);

$JsonResult[] = array(
    'A1' => $color[ROUND($_SESSION["A1"])],
    'B1' => $color[ROUND($_SESSION["B1"])],
    'A2' => $color[ROUND($_SESSION["A2"])],
    'B2' => $color[ROUND($_SESSION["B2"])],
    'CoA1' => ROUND($_SESSION["A1"]),
    'CoB1' => ROUND($_SESSION["B1"]),
    'CoA2' => ROUND($_SESSION["A2"]),
    'CoB2' => ROUND($_SESSION["B2"]));
//
//echo $jsonformat = json_encode($JsonResult);

//echo $pathResponse = json_encode($JsonPath + $JsonResult);

for ($i = 0; $i < sizeof($JsonPath); $i++) {
    array_push($JsonPath[$i], $JsonResult);
    $JsonFinal[] = $JsonPath[$i];
}

echo json_encode($JsonFinal);


/////////////////////////////////////////////////////////////////////////////

//if ($pilihan2A == "B2") {
//    $arrPilihan3A = ['exitLeft', 'B3'];
//    $pilihan3A = $arrPilihan3A[mt_rand(0, count($arrPilihan3A) - 1)];
//    echo $pilihan3A;
//} else if ($pilihan2A == "B3") {
//    $arrPilihan3B = ['B2', 'exitRight'];
//    $pilihan3B = $arrPilihan3B[mt_rand(0, count($arrPilihan3B) - 1)];
//    echo $pilihan3B;
//}
//
//if ($pilihan2B == "B2") {
//    $arrPilihan3A = ['exitLeft', 'B3'];
//    $pilihan3A = $arrPilihan3A[mt_rand(0, count($arrPilihan3A) - 1)];
//    echo $pilihan3A;
//} else if ($pilihan2B == "B3") {
//    $arrPilihan3B = ['B2', 'exitRight'];
//    $pilihan3B = $arrPilihan3B[mt_rand(0, count($arrPilihan3B) - 1)];
//    echo $pilihan3B;
//}
//
//////////////////////////////////////////////////////////////////////////////
//
//if ($pilihan3A == "B3") {
//    $pilihan4 = "exitRight";
//    echo $pilihan4;
//}
//
//if ($pilihan3B == "B2") {
//    $pilihan4 = "exitLeft";
//    echo $pilihan4;
//}
//
//
///////////////////////////////////////////////////////////////////////////////
//
//if ($pilihan1 == "A1") {
//    $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'A1');
//
//    if ($pilihan2A == "exitLeft"){
//        $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'EL');
//    }
//    else if ($pilihan2A == "B2"){
//        $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'B2');
//
//        if ($pilihan3A == "B3") {
//            $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'B3');
//            $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'ER');
//        }
//        else if ($pilihan3A == "exitLeft") {
//            $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'EL');
//        }
//    }
//    else if ($pilihan2A == "B3"){
//        $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'B3');
//
//        if ($pilihan3A == "B2") {
//            $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'B2');
//            $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'EL');
//        }
//        else if ($pilihan3A == "exitRight") {
//            $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'ER');
//        }
//    }
//} else if ($pilihan1 == "A2") {
//    $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'A2');
//
//    if ($pilihan2A == "exitRight"){
//        $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'ER');
//    }
//    else if ($pilihan2A == "B2"){
//        $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'B2');
//
//        if ($pilihan3A == "B3") {
//            $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'B3');
//            $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'ER');
//        }
//        else if ($pilihan3A == "exitLeft") {
//            $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'EL');
//        }
//    }
//    else if ($pilihan2A == "B3"){
//        $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'B3');
//
//        if ($pilihan3A == "B2") {
//            $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'B2');
//            $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'EL');
//        }
//        else if ($pilihan3A == "exitRight") {
//            $employees[] = array('x' => 150, 'y' => 200, 'count' => 1, 'ID' => 'ER');
//        }
//    }
//}
//
//
//
//echo $jsonformat = json_encode($employees);


//
//
//
//
//
//$employees[] = array('x'=>150,'y'=>200,'speed'=>200);
//
////for($i = 0; $i < $max;$i++)
////{
////    $user_info1 = $user_info[$i];
////    $user_info2 = $user_info1['field3'];
////
////    if (trim($user_info2) == '116 182 184 150'){
////        $x = 125;
////        $y = 50;
////        $speed = 2000;
////    }
////    elseif (trim($user_info2) == '27 06 252 184'){
////        $x = 50;
////        $y = 100;
////        $speed = 2000;
////    }
////    elseif (trim($user_info2) == '300 300 300 300'){
////        $x = 200;
////        $y = 100;
////        $speed = 2000;
////    }
////    else {
////        $x = 0;
////        $y = 0;
////        $speed = 0;
////    }
////
////    $employees[] = array('x'=>$x,'y'=>$y,'speed'=>$speed);
////}
//
//echo $jsonformat = json_encode($employees);
//
//$size = 0;
//
//echo $size;
//$size ++;
//
//
//if (isset($_SESSION["A1"])){
////    $_SESSION["A1"] = $_SESSION["A1"] + 1;
////    echo $_SESSION["A1"];
//    $_SESSION["A1"] = $_SESSION["A1"] + 1;
//    echo $_SESSION["A1"];
//}
//else{
//    echo "not set";
//}

?>