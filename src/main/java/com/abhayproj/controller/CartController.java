package com.abhayproj.controller;

import com.abhayproj.dto.CartRequest;
import com.abhayproj.dto.CartResponse;
import com.abhayproj.service.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public CartResponse addToCart(@Valid @RequestBody CartRequest request) {
        return cartService.addToCart(request);
    }

    @GetMapping
    public CartResponse getCart() {
        return cartService.getCart();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCart() {
        cartService.clearCart();
    }

    @PostMapping("/remove")
    public CartResponse removeFromCart(@Valid @RequestBody CartRequest request) {
        return cartService.removeFromCart(request);
    }
}
