package com.abhayproj.service;

import com.abhayproj.io.CartRequest;
import com.abhayproj.io.CartResponse;

public interface CartService {

    CartResponse addToCart(CartRequest request);

    CartResponse getCart();

    void clearCart();

    CartResponse removeFromCart(CartRequest cartRequest);
}
