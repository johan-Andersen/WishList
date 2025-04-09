package org.example.wishlist.repository;

import org.example.wishlist.Model.User;
import org.example.wishlist.Model.WishLists;
import org.example.wishlist.Model.Wishes;
import org.example.wishlist.Repository.WishlistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "classpath:h2init.sql"
)

public class WishListRepositoryTest {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Test
    void getAllWishLists_returnsCorrectWishLists() {
        // Act
        List<WishLists> wishLists = wishlistRepository.getAllWishLists();

        // Assert
        assertEquals(4, wishLists.size());

        WishLists first = wishLists.get(0);
        assertEquals(1, first.getUserID());
        assertEquals("GustavOenskeseddel", first.getName());
    }

    @Test
    void getWishListByWishListID_returnsCorrectWishlist() {
        WishLists wl = wishlistRepository.getWishListByWishListID(1);
        assertEquals("GustavOenskeseddel", wl.getName());
        assertEquals(1, wl.getUserID());
    }

    @Test
    void getWishlistsByUserID_returnsOnlyRelevant() {
        List<WishLists> lists = wishlistRepository.getWishlistsByUserID(1);
        assertEquals(2, lists.size());
    }

    @Test
    void getAllWishes_returnsAllWishes() {
        List<Wishes> wishes = wishlistRepository.getAllWishes();
        assertEquals(4, wishes.size());
        assertEquals("Nike sneakers", wishes.get(0).getName());
    }

    @Test
    void getWishesByWishListID_returnsCorrectWishes() {
        List<Wishes> wishes = wishlistRepository.getWishesByWishListID(1);
        assertEquals(1, wishes.size());
        assertEquals("Nike sneakers", wishes.get(0).getName());
    }

    @Test
    void getWishByWishID_returnsCorrectWish() {
        Wishes wish = wishlistRepository.getWishByWishID(1);
        assertEquals("Nike sneakers", wish.getName());
        assertEquals(799, wish.getPrice());
    }

    @Test
    void checkCredentials_returnsUser_whenCorrect() {
        User user = wishlistRepository.checkCredentials("gustav@gmail.com", "gustavpassword");
        assertNotNull(user);
        assertEquals("Gustav", user.getUsername());
    }

    @Test
    void checkCredentials_returnsNull_whenIncorrect() {
        User user = wishlistRepository.checkCredentials("wrong@email.com", "nope");
        assertNull(user);
    }

    @Test
    void addUser_addsCorrectly() {
        User newUser = new User(0, "TestUser", "test@email.com", "password");
        wishlistRepository.addUser(newUser);
        User result = wishlistRepository.checkCredentials("test@email.com", "password");
        assertNotNull(result);
        assertEquals("TestUser", result.getUsername());
    }

    @Test
    void addWishList_addsCorrectly() {
        WishLists newList = new WishLists();
        newList.setUserID(1);
        newList.setName("TestList");
        wishlistRepository.addWishList(newList);

        List<WishLists> lists = wishlistRepository.getWishlistsByUserID(1);
        assertTrue(lists.stream().anyMatch(w -> w.getName().equals("TestList")));
    }

    @Test
    void addWish_addsCorrectly() {
        Wishes wish = new Wishes(0, 1, "TestWish", "desc", 999, "https://test.com");
        wishlistRepository.addWish(wish);

        List<Wishes> wishes = wishlistRepository.getWishesByWishListID(1);
        assertTrue(wishes.stream().anyMatch(w -> w.getName().equals("TestWish")));
    }

    @Test
    void updateWish_updatesCorrectly() {
        Wishes wish = wishlistRepository.getWishByWishID(1);
        wish.setName("UpdatedName");
        wishlistRepository.updateWish(1, wish);

        Wishes updated = wishlistRepository.getWishByWishID(1);
        assertEquals("UpdatedName", updated.getName());
    }

    @Test
    void deleteWishFromWishList_deletesCorrectly() {
        wishlistRepository.deleteWishFromWishList(1);
        List<Wishes> wishes = wishlistRepository.getAllWishes();
        assertEquals(3, wishes.size());
    }

    @Test
    void deleteWishList_deletesCorrectly() {
        wishlistRepository.deleteWishList(1);
        List<WishLists> lists = wishlistRepository.getAllWishLists();
        assertEquals(3, lists.size());
    }
}
