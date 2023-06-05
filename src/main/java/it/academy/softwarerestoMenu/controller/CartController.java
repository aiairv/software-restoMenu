package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.CartItemDto;
import it.academy.softwarerestoMenu.dto.ResponseCartDto;
import it.academy.softwarerestoMenu.exceptions.CartNotFoundException;
import it.academy.softwarerestoMenu.exceptions.UserNotFoundException;
import it.academy.softwarerestoMenu.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addDish")
    public ResponseEntity<ResponseCartDto> addDishToCart(@RequestParam("userId") Long userId,
                                                @RequestParam("dishId") Long dishId) {
        try {
            return ResponseEntity.ok( cartService.addDishToCart(userId, dishId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @DeleteMapping("/deleteDish")
    public ResponseEntity<ResponseCartDto> deleteDishFromCart(@RequestParam("userId") Long userId,
                                                     @RequestParam("dishId") Long dishId) {
        try {

            return ResponseEntity.ok(cartService.deleteDishFromCart(userId, dishId));
        }catch (UserNotFoundException | CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @PostMapping("/{cartId}/dish/{dishId}/topping/{toppingId}")
    public ResponseEntity<String> addToppingToDishInCart(@PathVariable("cartId") Long cartId,
                                                         @PathVariable("dishId") Long dishId,
                                                         @PathVariable("toppingId") Long toppingId) {
        try {
            cartService.addToppingToDishInCart(cartId, dishId, toppingId);
            return ResponseEntity.ok("Топпинг добавлен к блюду в корзине");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла ошибка при добавлении топпинга к блюду в корзине");
        }
    }
    @DeleteMapping("/{cartId}/dish/{dishId}/topping/{toppingId}")
    public ResponseEntity<String> removeToppingFromDishInCart(@PathVariable("cartId") Long cartId,
                                                              @PathVariable("dishId") Long dishId,
                                                              @PathVariable("toppingId") Long toppingId) {
        try {
            cartService.removeToppingFromDishInCart(cartId, dishId, toppingId);
            return ResponseEntity.ok("Топпинг удален из блюда в корзине");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла ошибка при удалении топпинга из блюда в корзине");
        }
    }
    @GetMapping("/dishes")
    public ResponseEntity<ResponseCartDto> getAllDishesFromCart(@RequestParam("userId") Long userId) {
        try {
            ResponseCartDto cartItems = cartService.getAllDishesFromCart(userId);
            return ResponseEntity.ok(cartItems);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}


