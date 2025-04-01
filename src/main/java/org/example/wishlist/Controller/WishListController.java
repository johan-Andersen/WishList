package org.example.wishlist.Controller;

import org.example.wishlist.Model.User;
import org.example.wishlist.Model.WishLists;
import org.example.wishlist.Model.Wishes;
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

    @PostMapping("/delete/{wishListID}")
    public String deleteWishList(@PathVariable int wishListID) {
        wishListService.deleteWishList(wishListID);
        return "redirect:/wishlist/profilepage";
    }
    @GetMapping("/wishlistpage")
    public String getWishListPage(@RequestParam("id") int wishlistID, Model model) {
        model.addAttribute("wishes", wishListService.getWishesByWishListID(wishlistID));
        model.addAttribute("wishlist", wishListService.getWishListByWishListID(wishlistID));
        return "wishlistpage";
    }

    @GetMapping("/addwish")
    public String addwish(@RequestParam("id") int wishlistID, Model model) {
        Wishes wish = new Wishes();
        wish.setWishListID(wishlistID);
        model.addAttribute("wish", wish);
        return "addwish";
    }

    @PostMapping("/addwish")
    public String addWish(@ModelAttribute Wishes wish) {
        wishListService.addWish(wish);
        return "redirect:/wishlist/wishlistpage?id=" + wish.getWishListID();
    }




}
