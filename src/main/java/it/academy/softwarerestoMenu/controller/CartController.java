package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.entity.Cart;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//package it.academy.softwarerestoMenu.controller;
//
//import it.academy.softwarerestoMenu.entity.Cart;
//import it.academy.softwarerestoMenu.service.CartService;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("/cart")
//public class CartController {
//    CartService cartService;
//    @PostMapping
//    public Cart addToCart(Long userId, Cart cart){
//        return cartService.addToCart(userId,dish);
//    }
//}
@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private CartService cartService;

    @PostMapping("/add/{userId}/{dishId}")
    public ResponseEntity<Cart> addToCart(@PathVariable Long userId, @PathVariable Long dishId) {
        Cart cart = cartService.addToCart(userId, dishId);
        return ResponseEntity.ok(cart);
    }
}

