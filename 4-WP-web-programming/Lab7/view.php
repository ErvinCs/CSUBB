<?php
    class View {
        public function __construct() {
        }

        public function outputUser($user) {
            echo json_encode($user);
        }

        public function outputProducts($products) {
            echo json_encode($products);
        }

        public function outputCart($cart) {
            echo json_encode($cart);
        }
    }
?>