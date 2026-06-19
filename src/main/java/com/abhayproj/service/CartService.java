package com.abhayproj.service;

import com.abhayproj.dto.CartRequest;
import com.abhayproj.dto.CartResponse;

public interface CartService {

    CartResponse addToCart(CartRequest request);

    CartResponse getCart();

    void clearCart();

    CartResponse removeFromCart(CartRequest cartRequest);
}
