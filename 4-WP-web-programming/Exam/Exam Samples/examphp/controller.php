<?php
require_once 'model.php';
require_once 'view.php';
session_start();
class Controller {
    private $view;
    private $model;

    public function __construct() {
        $this->model = new Model();
        $this->view  = new View();
    }

    public function service() {
        if(isset($_GET['action']) && !empty($_GET['action'])) {
            switch ($_GET['action']) {
                case "getAllUsers":
                    $this->{$_GET['action']}();
                    break;
                case "updateUser":
                    $this->{$_GET['action']}($_GET['userId'],$_GET['username'], $_GET['password']);
                    break;
                case "addUser":
                case "getUser":
                    $this->{$_GET['action']}($_GET['username'], $_GET['password']);
                    break;
                case "getUserByUsername":
                    $this->{$_GET['action']}($_GET['username']);
                    break;
                case "deleteUser":
                    $this->{$_GET['action']}($_GET['userId']);
                    break;
                case "getAllItemsByUser":
                case "getAllItems":
                case "getNoItemsPerUser":
                    $this->{$_GET['action']}();
                    break;
                case "getItemById":
                    $this->{$_GET['action']}($_GET['itemId']);
                    break;
                case "getItemsPaged":
                    $this->{$_GET['action']}($_GET['page']);
                    break;
                case "removeItem":
                    $this->{$_GET['action']}($_GET['itemId']);
                    break;
                case "addItem":
                    $this->{$_GET['action']}($_GET['name'], $_GET['description'], $_GET['value']);
                    break;
                case "updateItem":
                    $this->{$_GET['action']}($_GET['itemId'], $_GET['name'], $_GET['description'], $_GET['value']);
                    break;

            }
        }
    }

    // ---------- Users ----------
    public function getUser($username, $password) {
        $user = $this->model->getUser($username, $password);
        $this->view->output($user);
        return $user;
    }
    public function getUserByUsername($username) {
        $user = $this->model->getUserByUsername($username);
        $this->view->output($user);
        return $user;
    }

    public function getAllUsers() {
        $users = $this->model->getAllUsers();
        $this->view->output($users);
        return $users;
    }

    public function addUser($username, $password) {
        $user = $this->model->addUser($username, $password);
        $this->view->output($user);
        return $user;
    }

    public function deleteUser($userId) {
        $user = $this->model->deleteUser($userId);
        $this->view->output($user);
        return $user;
    }

    public function updateUser($userId, $username, $password) {
        $user = $this->model->updateUser($userId, $username, $password);
        $this->view->output($user);
        return $user;
    }

    // ---------- Items ----------
    public function getAllItems() {
        $items = $this->model->getAllItems();
        $this->view->output($items);
        return $items;
    }

    public function getAllItemsByUser() {
        $userId = $_SESSION["UserId"];

        $items = $this->model->getAllItemsByUser($userId);
        $this->view->output($items);

        $_SESSION["UserId"] = $userId;
        return $items;
    }

    public function getItemById($itemId) {
        $item = $this->model->getItemById($itemId);
        $this->view->output($item);
        return $item;
    }

    public function getItemsPaged($page) {
        $items = $this->model->getItemsPaged($page);
        $this->view->output($items);
        return $items;
    }

    public function getNoItemsPerUser() {
        $items = $this->model->getNoItemsPerUser();
        $this->view->output($items);
        return $items;
    }

    public function removeItem($itemId) {
        $deleted = $this->model->removeItem($itemId);

        $userId = $_SESSION["UserId"];

        $items = $this->model->getAllItemsByUser($userId);
        $this->view->output($items);

        $_SESSION["UserId"] = $userId;
        return $items;
    }

    public function addItem($name, $description, $value) {
        $userId = $_SESSION["UserId"];

        $newItem = $this->model->addItem($name, $description, $value, $userId);

        $items = $this->model->getAllItemsByUser($userId);
        $this->view->output($items);

        $_SESSION["UserId"] = $userId;
        return $items;
    }

    public function updateItem($itemId, $name, $description, $value) {
        $userId = $_SESSION["UserId"];

        $newItem = $this->model->updateItem($itemId, $name, $description, $value, $userId);

        $items = $this->model->getAllItemsByUser($userId);
        $this->view->output($items);

        $_SESSION["UserId"] = $userId;
        return $items;
    }

}

$controller = new Controller();
$controller->service(); // Service on receiving a request
?>