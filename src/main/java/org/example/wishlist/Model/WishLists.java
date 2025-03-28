package org.example.wishlist.Model;

import java.util.List;

public class WishLists {

    private int wishlistID; //please do not delete this, por favor
    private int userID;
    private String name;

    public WishLists(int userID, String name) {
        this.userID = userID;
        this.name = name;
    }
    public WishLists() {

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWishlistID() {
        return wishlistID;
    }
}
