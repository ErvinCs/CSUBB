<?php
class Item implements JSONSerializable {
    private $item_id;
    private $name;
    private $description;
    private $value;
    private $user_id;

    public function __construct($item_id, $name, $description, $value, $user_id) {
        $this->item_id = $item_id;
        $this->name = $name;
        $this->description = $description;
        $this->value = $value;
        $this->user_id = $user_id;
    }

    public function getId() {
        return $this->item_id;
    }

    public function getName() {
        return $this->name;
    }

    public function getDescription() {
        return $this->description;
    }

    public function getValue() {
        return $this->value;
    }

    public function getUserId() {
        return $this->user_id;
    }

    public function jsonSerialize() {
        return get_object_vars($this);
    }

}
?>