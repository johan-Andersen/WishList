package org.example.wishlist.Service;

import org.example.wishlist.Model.User;
import org.example.wishlist.Model.WishLists;
import org.example.wishlist.Model.Wishes;
import org.example.wishlist.Repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {

    private final WishlistRepository wishlistRepository;

    public WishListService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public void addUser(User user) {
        wishlistRepository.addUser(user);
    }

    public void addWishList(WishLists wishList) {
        wishlistRepository.addWishList(wishList);
    }

    public void addWish(Wishes wish) {
        wishlistRepository.addWish(wish);
    }

    public List<WishLists> getAllWishLists() {
        return wishlistRepository.getAllWishLists();
    }

    public List<Wishes> getAllWishes() {
        return wishlistRepository.getAllWishes();
    }

    public List<Wishes> getWishesByWishListID(int wishlistID) {
        return wishlistRepository.getWishesByWishListID(wishlistID);
    }

    public WishLists getWishListByWishListID(int ID) {
        return wishlistRepository.getWishListByWishListID(ID);
    }

    public void deleteWishList(int wishListId) {
        wishlistRepository.deleteWishList(wishListId);
    }

    public void deleteWishFromWishList(int wishID) {
        wishlistRepository.deleteWishFromWishList(wishID);
    }


}
