package org.example.wishlist.Model;

public class Wishes {

    private int wishID;
    private int wishListID;
    private String name;
    private String description;
    private int price;
    private String link;

    public Wishes(int wishID, int wishListID, String name, String description, int price, String link) {
        this.wishID = wishID;
        this.wishListID = wishListID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.link = link;

    }
    public Wishes() {

    }

    public void setWishID(int wishID) {
        this.wishID = wishID;
    }

    public int getWishListID() {
        return wishListID;
    }

    public void setWishListID(int wishListID) {
        this.wishListID = wishListID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getWishID() {
        return wishID;
    }
}