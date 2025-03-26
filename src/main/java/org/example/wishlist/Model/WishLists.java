package org.example.wishlist.Model;

import java.util.List;

public class WishLists {

    private List<Wishes> wishesList;

    public WishLists(List<Wishes> wishesList) {
        this.wishesList = wishesList;
    }

    public List<Wishes> getWishesList() {
        return wishesList;
    }

    public void setWishesList(List<Wishes> wishesList) {
        this.wishesList = wishesList;
    }

}
