package org.example.wishlist.Service;

import org.example.wishlist.Model.User;
import org.example.wishlist.Repository.WishlistRepository;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    private final WishlistRepository wishlistRepository;

    public WishListService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public void addUser(User user) {
        wishlistRepository.addUser(user);
    }



}
