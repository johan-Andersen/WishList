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

    //NEGATIVE TESTING FOR NONEXISTENT PAGE:
    @Test
    void viewNonExistentPage() throws Exception {
        mockMvc.perform(get("/nonexistent"))
                .andExpect(status().isNotFound());
    }

    //TESTING FOR EXISTING PAGES: /INDEX:
    @Test
    void viewFrontPageCorrect() throws Exception {
        mockMvc.perform(get("")).andExpect(status().isOk()).andExpect(view().name("index"));
    }

    //TESTING FOR EXISTING PAGES: /WISHLIST/SIGNUP:
    @Test
    void viewSignUpPage() throws Exception {
        mockMvc.perform(get("/wishlist/signup")).andExpect(status().isOk()).andExpect(view().name("signup"));
    }

    @Test
    void showProfilePage() throws Exception {
        mockMvc.perform(get("/wishlist/profilepage")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/wishlist/login"));

    }


    @Test
    void showProfilePageWithSessionAndWishlists() throws Exception {
        int userID = 12;
        List<WishLists> wishlists = Arrays.asList(
                new WishLists(1, userID, "Birthday"),
                new WishLists(2, userID, "Christmas")
        );

        when(wishListService.getWishListsByUserID(userID)).thenReturn(wishlists);

        mockMvc.perform(get("/wishlist/profilepage")
                        .sessionAttr("userID", userID)) // ADDS USERID TO SESSION
                .andExpect(status().isOk())
                .andExpect(view().name("profilepage"))
                .andExpect(model().attributeExists("wishlists"))
                .andExpect(model().attribute("wishlists", wishlists));

        verify(wishListService, times(1)).getWishListsByUserID(userID);
    }

    //ADD WISHLIST:
    //METHOD FOR CONFIRMING IT IS THE RIGHT PAGE THAT SHOWS UP
    @Test
    void showAddWishListPage() throws Exception {
        //METHOD FOR CONFIRMING IT IS THE RIGHT PAGE THAT SHOWS UP
        mockMvc.perform(get("/wishlist/addwishlist")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/wishlist/login"));

    }

    //TESTING METHOD WITH DATA
    @Test
    void testAddWishList() throws Exception {
        WishLists wishList = new WishLists();
        wishList.setName("Christmas");

        mockMvc.perform(post("/wishlist/addwishlist") // SIMULATES A POST REQUEST
                        .flashAttr("wishLists", wishList))// SIMULATES SENDING INSTANCE OF WISHLISTS TO THE CONTROLLER
                .andExpect(status().is3xxRedirection()) // MAKES SURE THAT IS A REDIRECT
                .andExpect(redirectedUrl("/wishlist/profilepage")); //REDIRECTS TO THIS ENDPOINT

        verify(wishListService, times(1)).addWishList(any(WishLists.class)); //ENSURES THAT THE METHOD IS ONLY CALLED ONCE
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

    @Test
    void getWishPage_ReturnsEditwishViewAndModel() throws Exception {
        int wishID = 10;
        Wishes dummyWish = new Wishes();
        dummyWish.setName("Dummy titel");
        dummyWish.setDescription("Dummy beskrivelse");
        dummyWish.setPrice(100);

        //MAKES SURE THE SERVICE RETURNS THE DUMMY WISH
        when(wishListService.getWishByWishID(wishID)).thenReturn(dummyWish);

        mockMvc.perform(get("/wishlist/editwish").param("id", String.valueOf(wishID)))
                .andExpect(status().isOk())
                .andExpect(view().name("editwish"))
                .andExpect(model().attributeExists("wish"))
                .andExpect(model().attribute("wish", dummyWish));
    }

    @Test
    void updateWish_ShouldRedirectAndInvokeService() throws Exception {
        int wishID = 42;

        mockMvc.perform(post("/wishlist/update/{wishID}", wishID)
                        .param("name", "Ny guitar")
                        .param("description", "Skal være rød og firkantet")
                        .param("price", "2500"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlist/profilepage"));

        //VERIFIES THE SERVICE METHOD IS CALLED WITH ANY WISHES OBJECT
        verify(wishListService, times(1)).updateWish(eq(wishID), any(Wishes.class));
    }

    @Test
    void deleteWish_ShouldRedirectAndInvokeService() throws Exception {
        int wishID = 55;

        mockMvc.perform(post("/wishlist/deleteWish/{wishID}", wishID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlist/profilepage"));

        // CONFIRMS THAT THE DELETE METHOD IS CALLES WITH THE RIGHT ID
        verify(wishListService, times(1)).deleteWishFromWishList(eq(wishID));
    }


}