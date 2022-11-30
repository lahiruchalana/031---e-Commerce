package com.lciresh.cartservice.controller;

import com.lciresh.cartservice.dto.UserItemDTO;
import com.lciresh.cartservice.model.Cart;
import com.lciresh.cartservice.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create-cart")
    public ResponseEntity<Cart> createNewCart(
            HttpServletRequest request
    ) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());
        return new ResponseEntity<>(cartService.createNewCart(token), HttpStatus.CREATED);
    }

    @PostMapping("/item-add-to-cart")
    public ResponseEntity<Cart> addItemToCart(
            @RequestBody UserItemDTO userItemDTO
    ) {
        return new ResponseEntity<>(cartService.addItemToCart(userItemDTO.getItemId(), userItemDTO.getUsername()), HttpStatus.CREATED);
    }

    @DeleteMapping("/item-remove-from-cart")
    public ResponseEntity<Cart> removeItemFromCart(
            @RequestBody UserItemDTO userItemDTO
    ) {
        return new ResponseEntity<>(cartService.removeItemFromCart(userItemDTO.getItemId(), userItemDTO.getUsername()), HttpStatus.NO_CONTENT);
    }


}
