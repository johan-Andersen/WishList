package org.example.wishlist.Model;

public class User {


    private int userID;      //userID isnt initialized because its auto incremented
    private String username;
    private String email;
    //mangler password
    private String password;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
