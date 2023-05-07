<?php
    $target_dir = "./memeParadis/front/memeparadis/src/assets/content/video/"; //image upload folder name
    $target_file = $target_dir . basename($_FILES["video"]["name"]);
    //moving multiple images inside folder
    if (move_uploaded_file($_FILES["video"]["tmp_name"], $target_file)) {
        $data = array("data" => "File is valid, and was successfully uploaded.");
        print json_encode($data);
    }
?>