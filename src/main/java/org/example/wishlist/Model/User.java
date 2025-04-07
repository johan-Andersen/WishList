package org.example.wishlist.Model;

public class User {
    private int userID;
    private String username;
    private String email;
    private String password;

    public User(int userID, String username, String email, String password) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    //Not implemented yet!!!
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    //Not implemented yet!!!
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
