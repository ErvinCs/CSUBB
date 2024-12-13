<?php
class DBUtils {
    private $host    = '127.0.0.1';
    private $db      = 'exam';
    private $user    = 'root';
    private $pass    = '';
    private $charset = 'utf8';

    public $pdo;
    public $error;

    public function __construct () {
        header("Access-Control-Allow-Origin: *");
        $dsn = "mysql:host=$this->host;dbname=$this->db;charset=$this->charset";
        $opt = array(PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
                     PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
                     PDO::ATTR_EMULATE_PREPARES   => false);
        try {
            $this->pdo = new PDO($dsn, $this->user, $this->pass, $opt);
        }
        catch(PDOException $e){
            $this->error = $e->getMessage();
            echo "Error connecting to the Database: " . $this->error;
        }
    }

    // ---------- Users ----------
    public function selectUser($username, $password) {
        $stmt = $this->pdo->query("SELECT * FROM users WHERE username='".$username."' AND password='".$password."'");
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }

    public function selectAllUsers() {
        $stmt = $this->pdo->query("SELECT * FROM users");
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }

    public function selectUserByUsername($username) {
        $stmt = $this->pdo->query("SELECT * FROM users WHERE username='".$username."'");
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }

    public function deleteUser($userId) {
        $rows = $this->pdo->exec("DELETE FROM users WHERE user_id=".$userId);
        return $rows;
    }

    public function addUser($username, $password) {
        $rows = $this->pdo->exec("INSERT INTO users (username, password) VALUES ('".$username."','".$password."')");
        return $rows;
    }

    public function updateUser($userId, $username, $password) {
        $rows = $this->pdo->exec("UPDATE users SET username='".$username."',password='".$password."' WHERE user_id=".$userId);
        return $rows;
    }

    // ---------- Items ----------
    public function addItem($name, $description, $value, $userId) {
        $rows = $this->pdo->exec("INSERT INTO items (name, description, value, user_id) VALUES ('".
            $name."','".$description."',".$value.",".$userId.")");
        return $rows;
    }

    public function removeItem($itemId) {
        $rows = $this->pdo->exec("DELETE FROM items WHERE item_id=".$itemId);
        return $rows;
    }

    public function updateItem($itemId, $name, $description, $value, $userId) {
        $rows = $this->pdo->exec("UPDATE items SET name='".$name."',description='".$description."',value=".
            $value." WHERE user_id=".$userId." AND item_id=".$itemId);
        return $rows;
    }

    public function getItemById($itemId) {
        $stmt = $this->pdo->query("SELECT * FROM items WHERE item_id=".$itemId);
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }

    public function getAllItems() {
        $stmt = $this->pdo->query("SELECT * FROM items");
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function getItemsByUser($userId) {
        $stmt = $this->pdo->query("SELECT * FROM items WHERE user_id=".$userId);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function getItemsPaged($page) {
        $pageBegin = ($page - 1) * 4;
        $pageEnd = $page + 3;
        $stmt = $this->pdo->query("SELECT * FROM items LIMIT ".$pageBegin.",".$pageEnd);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function getNoItemsPerUser() {
        $stmt = $this->pdo->query("SELECT users.user_id, users.username, (SELECT COUNT(items.item_id) FROM items WHERE items.user_id = users.user_id) AS item_count, (SELECT SUM(items.value) FROM items WHERE items.user_id = users.user_id) AS total_value FROM users");
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
}
?>