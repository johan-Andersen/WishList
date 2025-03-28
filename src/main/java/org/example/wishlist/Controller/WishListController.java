package org.example.wishlist.Controller;

import org.example.wishlist.Model.User;
import org.example.wishlist.Model.WishLists;
import org.example.wishlist.Service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("")
    public String viewFrontPage() {
        return "index";
    }

    @GetMapping("/signup")
    public String viewSignUpPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    @PostMapping("/signup")
    public String addUser(@ModelAttribute User user) {
        wishListService.addUser(user);
        return "redirect:/wishlist/profilepage";
    }

    @GetMapping("/profilepage")
    public String getProfilePage(Model model) {
        model.addAttribute("wishlists", wishListService.getAllWishLists());
        return "profilepage";

    }

    @GetMapping("/addwishlist")
    public String addWishList(Model model) {
        model.addAttribute("wishList", new WishLists());
        return "addwishlist";
    }

    @PostMapping("/addwishlist")
    public String addWishList(@ModelAttribute WishLists wishLists) {
        wishListService.addWishList(wishLists);
        return "redirect:/wishlist/profilepage";
    }



//    @GetMapping("")
//    public String getAllAttractions(Model model) {
//        List<TouristAttraction> attractions = touristService.getAllAttractions();
//        model.addAttribute("attractions", attractions);
//        return "attractions";
//    }

//    @GetMapping("")
//    public String getAllWishList(Model model) {
//
//        List<WishLists> allWishLists = wishListService.
//
//        model.addAttribute("wishlists", )
//    }




}
