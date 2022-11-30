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

    @PostMapping("/items/{itemId}/item-add-to-cart")
    public ResponseEntity<Cart> addItemToCart(
            @PathVariable("itemId") Long itemId,
            HttpServletRequest request
    ) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());
        return new ResponseEntity<>(cartService.addItemToCart(itemId, token), HttpStatus.CREATED);
    }

    @DeleteMapping("/items/{itemId}/item-remove-from-cart")
    public ResponseEntity<Cart> removeItemFromCart(
            @PathVariable("itemId") Long itemId,
            HttpServletRequest request
    ) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());
        return new ResponseEntity<>(cartService.removeItemFromCart(itemId, token), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Cart> getCart(
            HttpServletRequest request
    ) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());
        return new ResponseEntity<>(cartService.getCart(token), HttpStatus.OK);
    }


}
