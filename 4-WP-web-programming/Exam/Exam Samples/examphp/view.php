<?php
class View {
    public function __construct() {
    }

    public function output($obj) {
        echo json_encode($obj);
    }
}
?>
