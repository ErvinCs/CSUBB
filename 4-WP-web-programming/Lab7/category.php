<?php
    class Category implements JSONSerializable {
        private $category;

        public function __construct($category) {
            $this->category = $category;
        }

        public function getCategory() {
            return $this->category;
        }

        public function jsonSerialize() {
            return get_object_vars($this);
        }
    }

?>