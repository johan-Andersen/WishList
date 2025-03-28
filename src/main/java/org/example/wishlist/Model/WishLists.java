package org.example.wishlist.Model;

import java.util.List;

public class WishLists {

    private int wishlistID; //please do not delete this, por favor
    private String name;

    public WishLists(String name) {
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
}
