package org.example.wishlist.Controller;

import org.example.wishlist.Model.WishLists;
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
        WishLists wishList = new WishLists(); // Opretter en ønskeliste
        wishList.setId(wishListID);
        wishList.setName("Christmas");

        //MOCKUP WISHLIST
        when(wishListService.getWishListByWishListID(wishListID)).thenReturn(wishList);
        doNothing().when(wishListService).deleteWishList(wishListID); // MOCKS THE

        // Simuler POST-request til /delete/{wishListID}
        mockMvc.perform(post("/wishlist/delete/{wishListID}", wishListID))
                .andExpect(status().is3xxRedirection()) // Skal være en redirect
                .andExpect(redirectedUrl("/wishlist/profilepage")); // Skal redirecte til profil

        // Verificér at deleteWishList blev kaldt med det rigtige ID
        verify(wishListService, times(1)).deleteWishList(eq(wishListID)); // Sikrer det korrekte ID
    }


    @Test
    void getWishListPage() {

    }

    @Test
    void addWish() {
    }

    @Test
    void getWishPage() {
    }

    @Test
    void updateWish() {
    }

    @Test
    void deleteWish() {
    }
}