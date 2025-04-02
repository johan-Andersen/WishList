package org.example.wishlist.Model;

public class User {
    private String username;
    private String email;
    private String password; //Not implemented yet!!!

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User() {
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
}
