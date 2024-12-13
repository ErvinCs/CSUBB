<?php
    class Product implements JSONSerializable {
        private $id;
        private $name;
        private $price;
        private $category;

        public function __construct($id, $name, $price, $category) {
            $this->id = $id;
            $this->name = $name;
            $this->price = $price;
            $this->category = $category;
        }

        public function getId() {
            return $this->id;
        }

        public function getName() {
            return $this->name;
        }

        public function getPrice() {
            return $this->price;
        }

        public function getCategory() {
            return $this->category;
        }

        public function jsonSerialize() {
            return get_object_vars($this);
        }

    }
?>