package ubb.web.lab8.model;

public class User extends BaseEntity{
    private String username;
    private String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{id=" + id.toString() +
                ", username=" + this.username +
                ", password =" + this.password +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        return this.getUsername().equals(other.getUsername()) &&
                this.getPassword().equals(other.getPassword());
    }
}
