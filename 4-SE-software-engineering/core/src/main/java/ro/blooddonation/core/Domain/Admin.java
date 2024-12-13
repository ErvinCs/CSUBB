package ro.blooddonation.core.Domain;

public class Admin
{
    private String username;
    private String password;

    /**
     * Default constructor
     */
    public Admin() {}
    /**
     *
     * @param username: String; unique
     * @param password: String
     */
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}