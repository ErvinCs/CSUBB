package ubb.exam.model;

public class ItemUser extends BaseEntity {
    public String username;
    public Integer itemCount;
    public Integer totalValue;

    public ItemUser() {}

    public ItemUser(String username, Integer itemCount, Integer totalValue) {
        this.username = username;
        this.itemCount = itemCount;
        this.totalValue = totalValue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Integer getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Integer totalValue) {
        this.totalValue = totalValue;
    }
}
