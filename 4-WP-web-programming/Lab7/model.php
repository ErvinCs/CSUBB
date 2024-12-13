<?php

require_once 'config.php';
require_once 'user.php';
require_once 'product.php';

    class Model {
        private $db;

        public function __construct() {
            $this->db = new DBUtils();
        }

        public function getUser($username, $password) {
            $result = $this->db->selectUser($username, $password);

            if (!$result) {
                return new User(-1, 'No such user!', 'None');
            }

            return new User($result['id'], $result['username'], $result['password']);
        }

        public function getProductById($id) {
            $result = $this->db->selectProductById($id);
            return new Product($result['id'], $result['name'], $result['price'], $result['category']);
        }

        public function getKeyboards($page) {
            $results = $this->db->selectKeyboardsPage($page);
            $keyboards = array();
            foreach($results as $key => $val) {
                $keyboard = $val;
                array_push($keyboards, $keyboard);
            }
            return $keyboards;
        }

        public function getMice($page) {
            $results = $this->db->selectMicePage($page);
            $mice = array();
            foreach($results as $key => $val) {
                $mouse = $val;
                array_push($mice, $mouse);
            }
            return $mice;
        }

    }

?>