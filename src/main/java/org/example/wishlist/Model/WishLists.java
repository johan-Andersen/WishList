package org.example.wishlist.Model;

public class WishLists {
    private int wishlistID; //please do not delete this, por favor
    private String name;

    public WishLists(int wishlistID, String name) {
        this.wishlistID = wishlistID;
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

}
