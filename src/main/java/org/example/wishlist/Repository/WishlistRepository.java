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

    // Constructor to initialize JdbcTemplate
    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CRUD Methods (Create, Read, Update, Delete)

    // Adds a new user to the database (Create)
    public void addUser(User user) {
        String sql = "INSERT INTO Users (username, email, password) VALUES (?,?,?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword());
    }

    // Adds a new wishlist to the database (Create)
    public void addWishList(WishLists wishLists) {
        String sql = "INSERT INTO WishLists (userID, name) VALUES (?, ?)";
        jdbcTemplate.update(sql, wishLists.getUserID(), wishLists.getName());
    }

    // Adds a new wish to the database (Create)
    public void addWish(Wishes wish) {
        String sql = "INSERT INTO wishes (wishlistID, name, description, price, link) VALUES (?,?,?,?,?) ";
        jdbcTemplate.update(sql, wish.getWishListID(), wish.getName(), wish.getDescription(), wish.getPrice(), wish.getLink());
    }

    // Retrieves all wishlists from the database (Read)
    public List<WishLists> getAllWishLists() {
        String sql = "SELECT * FROM wishlists";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new WishLists(
                rs.getInt("wishListID"),
                rs.getInt("userID"),
                rs.getString("name")));

    }

    // Retrieves a specific wishlist by its ID (Read)
    public WishLists getWishListByWishListID(int ID) {
        String sql = "SELECT * FROM wishlists WHERE wishlistID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{ID}, (rs, rownum) -> new WishLists(
                rs.getInt("wishListID"),
                rs.getInt("userID"),
                rs.getString("name")));
    }

    public List<WishLists> getWishlistsByUserID(int userID) {

        String sql = "SELECT * FROM wishlists WHERE userID = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new WishLists(
                rs.getInt("wishListID"),
                rs.getInt("userID"),
                rs.getString("name")),
                userID);
    }

    // Retrieves all wishes from the database (Read)
    public List<Wishes> getAllWishes() {
        String sql = "SELECT * FROM wishes";
        return jdbcTemplate.query(sql, (rs, rownum) -> new Wishes(
                rs.getInt("wishID"),
                rs.getInt("wishListID"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getInt("price"),
                rs.getString("link")));
    }

    // Retrieves all wishes for a specific wishlist by its ID (Read)
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
    public Wishes getWishByWishID(int ID) {
        String sql = "SELECT * FROM wishes WHERE wishID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{ID}, (rs, rowNum) -> new Wishes(
                rs.getInt("wishID"),
                rs.getInt("wishListID"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getInt("price"),
                rs.getString("link")));
    }

    //UPDATE
    public void updateWish(int wishID, Wishes wish) {
        String sql = "UPDATE wishes SET name = ?, description = ?, price = ?, link = ? WHERE wishID = ?";
        jdbcTemplate.update(sql, wish.getName(), wish.getDescription(), wish.getPrice(), wish.getLink(), wishID);
    }

    // Deletes a wishlist from the database (Delete)
    public void deleteWishList(int wishListID) {
        String sql = "DELETE FROM wishlists WHERE wishlistID = ?";
        jdbcTemplate.update(sql, wishListID);
    }

    // Deletes a wish from a wishlist in the database (Delete)
    public void deleteWishFromWishList(int wishID) {
        String sql = "DELETE FROM wishes where wishID = ?";
        jdbcTemplate.update(sql, wishID);
    }

    public User checkCredentials(String email, String password) {
        String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";

        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new User(
                            rs.getInt("userID"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password")),
                    email, password);
        } catch (Exception e) {
            return null;
        }
    }



    // Example method to test the connection
}
