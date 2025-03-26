package org.example.wishlist.Model;

public class Wishes {

    private String name;
    private String description;
    private double price;
    private String link;


    public Wishes(String name, String description, double price, String link) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.link = link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}