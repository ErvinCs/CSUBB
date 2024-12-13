<?php
require_once 'model.php';
require_once 'view.php';
require_once 'product.php';
session_start();
    class Controller {
        private $view;
        private $model;

        public function __construct() {
            $this->model = new Model();
            $this->view = new View();
        }

        public function service() {
            if(isset($_GET['action']) && !empty($_GET['action'])) {
                if($_GET['action'] == "getUser") {
                    $this->{$_GET['action']}($_GET['user']);
                } else if ($_GET['action'] == "getKeyboards") {
                    $this->{$_GET['action']}($_GET['page']);
                } else if ($_GET['action'] == "getMice") {
                    $this->{$_GET['action']}($_GET['page']);
                } else if ($_GET['action'] == "editCart") {
                    $this->{$_GET['action']}($_GET['editAction'], $_GET['productId']);
                }
            }
        }

        public function editCart($editAction, $productId) {
            $product = $this->model->getProductById($productId);
            $cart = $_SESSION["cart"];
            if ($productId > 0) {
                if ($editAction === 'buy') {
                    array_push($cart, $product);
                } else if ($editAction === 'remove') {
                    if (($key = array_search($product, $cart)) !== false) {
                        unset($cart[$key]);
                    }
                }
                $_SESSION["cart"] = $cart;
            }
            $this->view->outputCart($cart);
            return $cart;
        }


        public function getUser($username, $password) {
            $user = $this->model->getUser($username, $password);
            $this->view->outputUser($user);
            return $user;
        }

        public function getKeyboards($page) {
            $keyboards = $this->model->getKeyboards($page);
            $this->view->outputProducts($keyboards);
            return $keyboards;
        }

        public function getMice($page) {
            $mice = $this->model->getMice($page);
            $this->view->outputProducts($mice);
            return $mice;
        }
    }

$controller = new Controller();
// Executed on receiving a request
$controller->service();
?>