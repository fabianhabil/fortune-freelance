public class User {
    private String name;
    private String username;
    private String password;

    // Constructor User
    User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // Getter name
    public String getName() {
        return this.name;
    }

    // Getter username
    public String getUsername() {
        return this.username;
    }

    // Setter name
    public void setName(String name) {
        this.name = name;
    }

    // Setter username
    public void setUsername(String username) {
        this.username = username;
    }

    // Setter password
    public void setPassword(String password) {
        this.password = password;
    }

    // Method for verify login
    // 1 = success
    // -1 = wrong password
    // 0 = user not found
    public int login(String username, String password) {
        if (username.equals(this.username)) {
            if (password.equals(this.password)) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }
}
