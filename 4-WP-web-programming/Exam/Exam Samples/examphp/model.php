<?php

require_once 'database.php';
require_once 'user.php';

class Model {
    private $db;

    public function __construct() {
        $this->db = new DBUtils();
    }


    // ---------- Users ----------
    public function getUser($username, $password) {
        $result = $this->db->selectUser($username, $password);

        if (!$result) {
            return new User(-1, 'No such user!', 'None');
        }

        return new User($result['user_id'], $result['username'], $result['password']);
    }

    public function getUserByUsername($username) {
        $result = $this->db->selectUserByUsername($username);

        if (!$result) {
            return new User(-1, 'No such user!', 'None');
        }

        return new User($result['user_id'], $result['username'], $result['password']);
    }

    public function getAllUsers() {
        $result = $this->db->selectAllUsers();
        $users = array();
        foreach($result as $key => $val) {
            $user = $val;
            array_push($users, $user);
        }
        return $users;
    }

    public function addUser($username, $password) {
        return $this->db->addUser($username, $password);
    }

    public function deleteUser($userId) {
        return $this->db->deleteUser($userId);
    }

    public function updateUser($userId, $username, $password) {
        return $this->db->updateUser($userId, $username, $password);
    }

    // ---------- Items ----------
    public function getAllItems() {
        $result = $this->db->getAllItems();
        $items = array();
        foreach($result as $key => $val) {
            $item = $val;
            array_push($items, $item);
        }
        return $items;
    }

    public function getAllItemsByUser($userId) {
        $result = $this->db->getItemsByUser($userId);
        $items = array();
        foreach($result as $key => $val) {
            $item = $val;
            array_push($items, $item);
        }
        return $items;
    }

    public function getItemById($itemId) {
        $result = $this->db->getItemById($itemId);
        $items = array();
        foreach($result as $key => $val) {
            $item = $val;
            array_push($items, $item);
        }
        return $items;
    }

    public function getItemsPaged($page) {
        $result = $this->db->getItemsPaged($page);
        $items = array();
        foreach($result as $key => $val) {
            $item = $val;
            array_push($items, $item);
        }
        return $items;
    }

    public function getNoItemsPerUser() {
        $result = $this->db->getNoItemsPerUser();
        $items = array();
        foreach($result as $key => $val) {
            $item = $val;
            array_push($items, $item);
        }
        return $items;
    }

    public function removeItem($itemId) {
        return $this->db->removeItem($itemId);
    }

    public function addItem($name, $description, $value, $userId) {
        return $this->db->addItem($name, $description, $value, $userId);
    }

    public function updateItem($itemId, $name, $description, $value, $userId) {
        return $this->db->updateItem($itemId, $name, $description, $value, $userId);
    }

}
?>