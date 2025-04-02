package org.example.wishlist.Controller;

import org.example.wishlist.Model.User;
import org.example.wishlist.Model.WishLists;
import org.example.wishlist.Model.Wishes;
import org.example.wishlist.Service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    // ------------------------------------ INDEX PAGE --------------------------------------------
    @GetMapping("")
    public String viewFrontPage() {
        return "index";
    }

    // ------------------------------------- SIGNUP --------------------------------------------
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

    // ------------------------------------- PROFILE PAGE --------------------------------------------
    @GetMapping("/profilepage")
    public String getProfilePage(Model model) {
        model.addAttribute("wishlists", wishListService.getAllWishLists());
        return "profilepage";
    }

    // ------------------------------------- ADD A WISHLIST --------------------------------------------
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

    // ------------------------------------- DELETE A WISHLIST --------------------------------------------
    @PostMapping("/delete/{wishListID}")
    public String deleteWishList(@PathVariable int wishListID) {
        wishListService.deleteWishList(wishListID);
        return "redirect:/wishlist/profilepage";
    }

    // ------------------------------------- WISHLIST PAGE --------------------------------------------
    @GetMapping("/wishlistpage")
    public String getWishListPage(@RequestParam("id") int wishlistID, Model model) {
        model.addAttribute("wishes", wishListService.getWishesByWishListID(wishlistID));
        model.addAttribute("wishlist", wishListService.getWishListByWishListID(wishlistID));
        return "wishlistpage";
    }

    // ------------------------------------- ADD A WISH --------------------------------------------
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

    // ------------------------------------- EDIT A WISH --------------------------------------------
    @GetMapping("/editwish")
    public String getWishPage(@RequestParam("id") int wishID, Model model) {
        model.addAttribute("wish", wishListService.getWishByWishID(wishID));
        return "editwish";
    }

    // ------------------------------------- UPDATE WISH --------------------------------------------
    @PostMapping("/update/{wishID}")
    public String updateWish(@PathVariable int wishID, @ModelAttribute Wishes wish) {
        wishListService.updateWish(wishID, wish);
        return "redirect:/wishlist/profilepage";
    }

    @PostMapping("/deleteWish/{wishID}")
    public String deleteWish(@PathVariable int wishID) {
        wishListService.deleteWishFromWishList(wishID);
        return "redirect:/wishlist/profilepage";
    }

}
