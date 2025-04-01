package org.example.wishlist.Repository;

import org.example.wishlist.Model.User;
import org.example.wishlist.Model.WishLists;
import org.example.wishlist.Model.Wishes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


@Repository
public class WishlistRepository {

    @Value("${DEV_DATABASE_URL}")
    private String dbURL;
    @Value("${DEV_USERNAME}")
    private String username;
    @Value("${DEV_PASSWORD}")
    private String password;

    private final JdbcTemplate jdbcTemplate;

    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // C (create) R (read) U (update) D (delete)

    // Create

    public void addUser(User user) {
        String sql = "INSERT INTO Users (username, email) VALUES (?,?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail());
    }

    public void addWishList(WishLists wishLists) {
        String sql = "INSERT INTO WishLists (name) VALUES (?)";
        jdbcTemplate.update(sql, wishLists.getName());
    }

    public void addWish(Wishes wish) {
        String sql = "INSERT INTO wishes (wishlistID, name, description, price, link) VALUES (?,?,?,?,?) ";
        jdbcTemplate.update(sql, wish.getWishListID(), wish.getName(), wish.getDescription(), wish.getPrice(), wish.getLink());
    }

    // Read
    // Retrieve all the wishes from the SQL database.
    public List<WishLists> getAllWishLists() {
        String sql = "SELECT * FROM wishlists";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new WishLists(
                rs.getInt("wishListID"),
                rs.getString("name")));

    }

    public WishLists getWishListByWishListID(int ID) {
        String sql = "SELECT * FROM wishlists WHERE wishlistID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{ID}, (rs, rownum) -> new WishLists(
                rs.getInt("wishListID"),
                rs.getString("name")));
    }


    public List<Wishes> getAllWishes() {
        String sql = "SELECT * FROM wishes";
        return jdbcTemplate.query(sql, (rs, rownum) -> new Wishes(
                rs.getInt("wishListID"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getInt("price"),
                rs.getString("link")));
    }

    public List<Wishes> getWishesByWishListID(int ID) {
        String sql = "SELECT * FROM wishes WHERE wishlistID = ?";
        return jdbcTemplate.query(sql, new Object[]{ID}, (rs, rownum) -> new Wishes(
                rs.getInt("wishID"),
                rs.getInt("wishListID"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getInt("price"),
                rs.getString("link")));
    }

    //DELETE
    public void deleteWishList(int wishListID) {
        String sql = "DELETE FROM wishlists WHERE wishlistID = ?";
        jdbcTemplate.update(sql, wishListID);
    }

    public void deleteWishFromWishList(int wishID) {
        String sql = "DELETE FROM wishes where wishID = ?";
        jdbcTemplate.update(sql, wishID);

    }


    // Example method to test the connection


}
