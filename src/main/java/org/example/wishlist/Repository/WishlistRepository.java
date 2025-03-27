package org.example.wishlist.Repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;


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

    // Example method to test the connection





}
