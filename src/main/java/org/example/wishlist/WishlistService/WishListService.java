package org.example.wishlist.WishlistService;

import org.example.wishlist.Repository.WishlistRepository;

public class WishListService {

    private final WishlistRepository wishlistRepository;

    public WishListService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }


}
