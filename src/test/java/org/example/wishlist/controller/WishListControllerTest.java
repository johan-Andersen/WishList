package org.example.wishlist.controller;

import org.example.wishlist.Model.WishLists;
import org.example.wishlist.Model.Wishes;
import org.example.wishlist.Service.WishListService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishListController.class)
class WishListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishListService wishListService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {

    }

    //NEGATIVE TESTING FOR NONEXISTENT PAGE: --------------------------------------------------------------------------
    @Test
    void viewNonExistentPage() throws Exception {
        mockMvc.perform(get("/nonexistent"))
                .andExpect(status().isNotFound());
    }

    //TESTING FOR EXISTING PAGES: /INDEX:---------------------------------------------------------------------------------
    @Test
    void viewFrontPageCorrect() throws Exception {
        mockMvc.perform(get("")).andExpect(status().isOk()).andExpect(view().name("index"));
    }

    //TESTING FOR EXISTING PAGES: /WISHLIST/SIGNUP:----------------------------------------------------------------------------
    @Test
    void viewSignUpPage() throws Exception {
        mockMvc.perform(get("/wishlist/signup")).andExpect(status().isOk()).andExpect(view().name("signup"));
    }

    @Test
    void addUser() {

    }

    @Test
    void showProfilePage() throws Exception {
        mockMvc.perform(get("/wishlist/profilepage")).andExpect(status().isOk()).andExpect(view().name("profilepage"));

    }


    @Test
    void showProfilePageWithWishlists() throws Exception {
        //TODO FIX SO METHOD FITS TO CONSTRUCTOR
        List<WishLists> wishlists = Arrays.asList(new WishLists(1, "birhtday"), new WishLists(2, "")); //Instances of wishLists so we can run the test
        when(wishListService.getAllWishLists()).thenReturn(wishlists); //Makes the method getAllWishLists return the mockup list and not the database

        mockMvc.perform(get("/wishlist/profilepage"))
                .andExpect(status().isOk())
                .andExpect(view().name("profilepage"))
                .andExpect(model().attributeExists("wishlists"))
                .andExpect(model().attribute("wishlists", wishlists));

        verify(wishListService, times(1)).getAllWishLists();
    }

    //ADD WISHLIST: :-------------------------------------------------------------------------------------------------
    //METHOD FOR CONFIRMING IT IS THE RIGHT PAGE THAT SHOWS UP
    @Test
    void showAddWishListPage() throws Exception {
        //METHOD FOR CONFIRMING IT IS THE RIGHT PAGE THAT SHOWS UP
        mockMvc.perform(get("/wishlist/addwishlist")).andExpect(status().isOk()).andExpect(view().name("addwishlist"));

    }

    //TESTING METHOD WITH DATA
    @Test
    void testAddWishList() throws Exception {
        WishLists wishList = new WishLists();
        wishList.setName("Christmas");

        mockMvc.perform(post("/wishlist/addwishlist") //Simulates a post request.
                        .flashAttr("wishLists", wishList))// Simulates sending instance of wishlists to the controller.
                .andExpect(status().is3xxRedirection()) // Makes sure that it is a redirect
                .andExpect(redirectedUrl("/wishlist/profilepage")); // Redirects to this endpoint.

        verify(wishListService, times(1)).addWishList(any(WishLists.class)); // Ensures that the method is only called once
    }

    @Test
    void testDeleteWishList() throws Exception {
        int wishListID = 1;
        WishLists wishList = new WishLists(); // INSTANCE OF WISHLIST
        wishList.setUserID(wishListID); //SETS ID TO 1
        wishList.setName("Christmas"); //SETS NAME TO "CHRISTMAS"

        when(wishListService.getWishListByWishListID(wishListID)).thenReturn(wishList); //MOCKUP WISHLIST IS GETTING RETURNED WHEN THE METHOD IS CALLED
        doNothing().when(wishListService).deleteWishList(wishListID); // MOCKS THE DELETE METHOD / MAKES SURE IT DOESNT ACTUALLY DELETE ANYTHING

        mockMvc.perform(post("/wishlist/delete/{wishListID}", wishListID)) // SIMULATES POST REQUEST TO /delete/{wishListID}
                .andExpect(status().is3xxRedirection()) // REDIRECT
                .andExpect(redirectedUrl("/wishlist/profilepage")); //WHERE IT SHOULD REDIRECT TO

        verify(wishListService, times(1)).deleteWishList(eq(wishListID));  // VERIFIES THAT deleteWishList WAS CALLED WITH THE CORRECT ID
    }


    @Test
    void getAddWishPage() throws Exception {
        int wishlistID = 1;

        mockMvc.perform(get("/wishlist/addwish").param("id", String.valueOf(wishlistID)))
                .andExpect(status().isOk()) // EXPECTS CODE 200 OK
                .andExpect(view().name("addwish")) //CHECKS THAT IT RETURNS THE HTML FILE addwish
                .andExpect(model().attributeExists("wish")) //CHECKS THAT THE MODEL HAS THE ATTRIBUTE WISH
                .andExpect(model().attribute("wish", hasProperty("wishListID", equalTo(wishlistID)))); // CHECKS THAT wish HAS THE CORRECT wishListID
    }

    @Test
    void testPostUpdateWish() throws Exception {
        int wishID = 1;
        Wishes wish = new Wishes();
        wish.setWishID(wishID);
        wish.setName("NikeSko");

        doNothing().when(wishListService).updateWish(wishID, wish);

        mockMvc.perform(post("/update/{wishID}", wishID)
                        .flashAttr("wish", wish))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlist/profilepage"));

        verify(wishListService, times(1)).updateWish(wishID, wish);
    }


    @Test
    void getEditWishPage() throws Exception {
        int wishID = 1;
        Wishes wish = new Wishes();
        wish.setWishID(1);
        wish.setName("NikeSKo");

        when(wishListService.getWishByWishID(wishID)).thenReturn(wish);

        mockMvc.perform(get("/wishlist/editwish").param("id", String.valueOf(wishID)))
                .andExpect(status().isOk())
                .andExpect(view().name("editwish"))
                .andExpect(model().attributeExists("wish"));
    }


    /*
    @Test
    void testPostUpdateWish() throws Exception {
        int wishID = 1;
        Wishes wish = new Wishes();
        wish.setWishID(wishID);
        wish.setName("NikeSko");

        when(wishListService.getWishByWishID(wishID)).thenReturn(wish);
        doNothing().when(wishListService).updateWish(wishID, wish);

        mockMvc.perform(post("/wishlist/update/{wishID}", wishID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlist/profilepage"));

        verify(wishListService, times(1)).updateWish(wishID, wish);

    }
*/
    @Test
    void deleteWish() {


    }
}