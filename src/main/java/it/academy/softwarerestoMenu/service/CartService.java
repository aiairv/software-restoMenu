package it.academy.softwarerestoMenu.service;//package it.academy.softwarerestoMenu.service;

import it.academy.softwarerestoMenu.entity.Cart;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.entity.User;
import it.academy.softwarerestoMenu.repository.CartRepository;
import it.academy.softwarerestoMenu.repository.DishRepository;
import it.academy.softwarerestoMenu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class    CartService {
    CartRepository cartRepository;
    UserRepository userRepository;
    DishRepository dishRepository;
//    private Map<Long, Dish> cart = new HashMap<>();


//
//    public CartDto addDishToCart(UserDto userDto, CartDto cartDto, DishDto dishDto) {
//        Cart cart = new Cart();
//        User user = userRepository.
//                ingredient.setId(ingredientDto.getId());
//        ingredient.setName(ingredientDto.getName());
//        ingredient = ingredientRepository.save(ingredient);
//        ingredientDto.setId(ingredient.getId());
//        return ingredientDto;
//    }
//
//
//    public CartDto entityToDto(Cart cart) {
//        CartDto dto = new CartDto();
//        dto.setId(cart.getId());
//        return dto;
//    }
//
//
//
//
//    public Cart addToCart(Long userId, Dish dish, Long cartId) {
//        Cart cart = cartRepository.findById(cartId).
//                orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + cartId);
//        User user = userRepository.findById(userId).
//                orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId);
//        ArrayList<Dish> dishes = new ArrayList<>();
//        dishes.add(dish);
//        cart.setUser(user);
//        cart.setDishes(dishes);
//        return cartRepository.save(cart);
//    }
    public Cart addToCart(Long userId, Long dishId) {
        Cart cart = new Cart();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(dishRepository.findById(dishId).orElseThrow(()-> new IllegalArgumentException("Dish not found with id: " + dishId)));
        cart.setUser(user);
        cart.setDishes(dishes);
        return cartRepository.save(cart);
    }
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + cartId));
    }

    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}






//
//import it.academy.softwarerestoMenu.entity.User;
//import it.academy.softwarerestoMenu.entity.Cart;
//import it.academy.softwarerestoMenu.entity.Dish;
//import it.academy.softwarerestoMenu.repository.CartRepository;
//import it.academy.softwarerestoMenu.repository.DishRepository;
//import it.academy.softwarerestoMenu.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class CartService {
//    private final CartRepository cartRepository;
//    private final DishRepository dishRepository;
//    private final UserRepository userRepository;
//
//    public CartService(CartRepository cartRepository, DishRepository dishRepository, UserRepository userRepository) {
//        this.cartRepository = cartRepository;
//        this.dishRepository = dishRepository;
//        this.userRepository = userRepository;
//    }
//
//    public void addDishToCart(Long userId, Long dishId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
//
//        Dish dish = dishRepository.findById(dishId)
//                .orElseThrow(() -> new IllegalArgumentException("Dish not found with id: " + dishId));
//
//        Cart cart = user.getCart();
//        if (cart == null) {
//            cart = new Cart();
//            cart.setUser(user);
//        }
//
//        cart.getDishes().add(dish);
//        cartRepository.save(cart);
//    }
//}
//
//}
