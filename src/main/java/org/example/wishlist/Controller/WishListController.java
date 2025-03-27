package org.example.wishlist.Controller;

import org.example.wishlist.Model.WishLists;
import org.example.wishlist.Repository.WishlistRepository;
import org.example.wishlist.WishlistService.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }


    @GetMapping("")
    public String getAllWishList(Model model) {

        List<WishLists> allWishLists = wishListService.

        model.addAttribute("wishlists", )
    }



}
