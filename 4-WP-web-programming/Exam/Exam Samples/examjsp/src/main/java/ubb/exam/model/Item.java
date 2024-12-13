package ubb.exam.model;

public class Item extends BaseEntity {
    private String name;
    private String description;
    private Integer value;
    private Long userId;

    public Item() {}

    public Item(String name, String description, Integer value, Long userId) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Item{id=" + id +
                "name=" + name +
                "description=" + description +
                "value=" + value +
                "userId=" + userId +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Item))
            return false;
        Item other = (Item)obj;
        return this.name.equals(other.getName()) &&
                this.value.equals(other.value);
    }
}
