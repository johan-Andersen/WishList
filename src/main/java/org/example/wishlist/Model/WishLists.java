package org.example.wishlist.Model;

import java.util.List;

public class WishLists {

    private String wishlistID;
    private String userID;
    private String name;

    public WishLists(String userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWishlistID() {
        return wishlistID;
    }
}
