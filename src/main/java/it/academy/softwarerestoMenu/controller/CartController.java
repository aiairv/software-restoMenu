package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.CartItemDto;
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
    public ResponseEntity<String> addDishToCart(@RequestParam("userId") Long userId,
                                                @RequestParam("dishId") Long dishId) {
        try {
            cartService.addDishToCart(userId, dishId);
            return ResponseEntity.ok("Блюдо добавлено в корзину");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла ошибка при добавлении блюда в корзину");
        }
    }
    @DeleteMapping("/deleteDish")
    public ResponseEntity<String> deleteDishFromCart(@RequestParam("userId") Long userId,
                                                     @RequestParam("dishId") Long dishId) {
        try {
            cartService.deleteDishFromCart(userId, dishId);
            return ResponseEntity.ok("Блюдо удалено из корзины");
        }catch (UserNotFoundException | CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла ошибка при удалении блюда из корзины");
        }
    }
//    @PutMapping("/{cartId}/dish/{dishId}/topping/{toppingId}")
//    public ResponseEntity<String> addToppingToDishInCart(@PathVariable("cartId") Long cartId,
//                                                         @PathVariable("dishId") Long dishId,
//                                                         @PathVariable("toppingId") Long toppingId) {
//        try {
//            cartService.addToppingToDishInCart(cartId, dishId, toppingId);
//            return ResponseEntity.ok("Топпинг добавлен к блюду в корзине");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Произошла ошибка при добавлении топпинга к блюду в корзине");
//        }
//    }
//    @DeleteMapping("/{cartId}/dish/{dishId}/topping/{toppingId}")
//    public ResponseEntity<String> removeToppingFromDishInCart(@PathVariable("cartId") Long cartId,
//                                                              @PathVariable("dishId") Long dishId,
//                                                              @PathVariable("toppingId") Long toppingId) {
//        try {
//            cartService.removeToppingFromDishInCart(cartId, dishId, toppingId);
//            return ResponseEntity.ok("Топпинг удален из блюда в корзине");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Произошла ошибка при удалении топпинга из блюда в корзине");
//        }
//    }
    @GetMapping("/dishes")
    public ResponseEntity<List<CartItemDto>> getAllDishesFromCart(@RequestParam("userId") Long userId) {
        try {
            List<CartItemDto> cartItems = cartService.getAllDishesFromCart(userId);
            return ResponseEntity.ok(cartItems);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}


