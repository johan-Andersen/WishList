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
    // Read

   // Retrieve all the wishes from the SQL database.
    public List<WishLists> getAllWishLists() {
        String sql = "SELECT * FROM wishlists";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new WishLists(
                rs.getString("name")));

    }


    // Example method to test the connection


}
