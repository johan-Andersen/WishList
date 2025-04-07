package org.example.wishlist.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.wishlist.Model.User;
import org.example.wishlist.Model.WishLists;
import org.example.wishlist.Model.Wishes;
import org.example.wishlist.Service.WishListService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
public class WishListController {
    private final WishListService wishListService;

    // Constructor to initialize WishListService
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    // CRUD Methods (Create, Read, Update, Delete)

    // Displays the front page (Read)
    @GetMapping("")
    public String viewFrontPage() {
        return "index";
    }

    // Displays the sign-up page (Read)
    @GetMapping("/signup")
    public String viewSignUpPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    // Adds a new user and redirects to the profile page (Create)
    @PostMapping("/signup")
    public String addUser(@ModelAttribute User user) {
        wishListService.addUser(user);
        return "redirect:/wishlist/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
    @PostMapping("/checkcredentials")
    public String checkCredentials(@RequestParam String email, @RequestParam String password, HttpSession session) {

        User user = wishListService.checkCredentials(email, password);

        if (user != null) {
            session.setAttribute("userID", user.getUserID());
            return "redirect:/wishlist/profilepage";
        } else {
            return "redirect:/wishlist/login"; // Reload login page with an error message
        }
    }

    // Displays the user's profile page with all wishlists (Read)
    @GetMapping("/profilepage")
    public String getProfilePage(HttpSession session, Model model) {
        Integer userID = (Integer)  session.getAttribute("userID");

        if(userID == null) {
            return "redirect:/wishlist/login";
        }

        model.addAttribute("wishlists", wishListService.getWishListsByUserID(userID));
        return "profilepage";
    }

    // Displays the page to add a new wishlist (Read)
    @GetMapping("/addwishlist")
    public String addWishList(HttpSession session, Model model) {
        Integer userID = (Integer) session.getAttribute("userID");

        if(userID == null) {
            return "redirect:/wishlist/login";
        }

        WishLists wishList = new WishLists();
        wishList.setUserID(userID);


        System.out.println("userid is : " + wishList.getUserID());

        model.addAttribute("wishList", wishList);
        return "addwishlist";
    }

    // Adds a new wishlist and redirects to the profile page (Create)
    @PostMapping("/addwishlist")
    public String addWishList(@ModelAttribute WishLists wishLists) {
        wishListService.addWishList(wishLists);
        return "redirect:/wishlist/profilepage";
    }

    // Deletes a wishlist and redirects to the profile page (Delete)
    @PostMapping("/delete/{wishListID}")
    public String deleteWishList(@PathVariable int wishListID) {
        wishListService.deleteWishList(wishListID);
        return "redirect:/wishlist/profilepage";
    }

    // Displays a specific wishlist and its wishes (Read)
    @GetMapping("/wishlistpage")
    public String getWishListPage(@RequestParam("id") int wishlistID, HttpSession session, Model model) {
        Integer userID = (Integer) session.getAttribute("userID");

        if(userID == null) {
            return "redirect:/wishes/login";
        }

        model.addAttribute("wishes", wishListService.getWishesByWishListID(wishlistID));
        model.addAttribute("wishlist", wishListService.getWishListByWishListID(wishlistID));
        return "wishlistpage";
    }

    // Displays the page to add a new wish to a wishlist (Read)
    @GetMapping("/addwish")
    public String addwish(@RequestParam("id") int wishlistID, Model model) {
        Wishes wish = new Wishes();
        wish.setWishListID(wishlistID);
        model.addAttribute("wish", wish);
        return "addwish";
    }

    // Adds a new wish to a wishlist and redirects to the wishlist page (Create)
    @PostMapping("/addwish")
    public String addWish(@ModelAttribute Wishes wish) {
        wishListService.addWish(wish);
        return "redirect:/wishlist/wishlistpage?id=" + wish.getWishListID();
    }

    // Deletes a wish from a wishlist and redirects to the profile page (Delete)
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
