package org.example.wishlist.Model;

public class WishLists {
    private int wishlistID; //please do not delete this, por favor
    private int userID;
    private String name;

    public WishLists(int wishlistID, int userID, String name) {
        this.wishlistID = wishlistID;
        this.userID = userID;
        this.name = name;
    }

    public WishLists() {
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

    //Not implemented yet!!!
    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


}
