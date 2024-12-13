<?php
class ItemUser implements JSONSerializable {
    private $item_id;
    private $username;
    private $itemCount;
    private $totalValue;

    public function __construct($item_id, $username, $itemCount, $totalValue) {
        $this->item_id = $item_id;
        $this->username = $username;
        $this->itemCount = $itemCount;
        $this->totalValue = $totalValue;
    }

    public function getId() {
        return $this->item_id;
    }

    public function getUsername() {
        return $this->username;
    }

    public function getItemCount() {
        return $this->itemCount;
    }

    public function getTotalValue() {
        return $this->totalValue;
    }

    public function jsonSerialize() {
        return get_object_vars($this);
    }

}
?>