<?php
class User implements JSONSerializable {
    private $user_id;
    private $username;
    private $password;

    public function __construct($user_id, $username, $password) {
        $this->user_id  = $user_id;
        $this->username = $username;
        $this->password = $password;
    }

    public function getId() {
        return $this->user_id;
    }

    public function getUsername() {
        return $this->username;
    }

    public function getPassword() {
        return $this->password;
    }

    public function jsonSerialize() {
        return get_object_vars($this);
    }
}
?>