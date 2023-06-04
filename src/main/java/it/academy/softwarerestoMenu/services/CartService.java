package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.CartItemDto;
import it.academy.softwarerestoMenu.entity.Cart;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.entity.User;
import it.academy.softwarerestoMenu.exceptions.CartNotFoundException;
import it.academy.softwarerestoMenu.exceptions.UserNotFoundException;
import it.academy.softwarerestoMenu.repository.CartRepository;
import it.academy.softwarerestoMenu.repository.DishRepository;
import it.academy.softwarerestoMenu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class    CartService {
    private CartRepository cartRepository;
    private UserRepository userRepository;
    private DishRepository dishRepository;

    public String addDishToCart(Long userId, Long dishId) {
        User user = userRepository.getUserById(userId);
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }
        Dish dish = dishRepository.getDishById(dishId);
        List<Dish> dishes = cart.getDishes();
        if (dishes == null) {
            dishes = new ArrayList<>();
        }
        dishes.add(dish);
        cart.setDishes(dishes);
        cartRepository.save(cart);
        return "Блюдо добавлено";
    }


        public String deleteDishFromCart(Long userId, Long dishId) {
            User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            Cart cart = user.getCart();
            if (cart == null) {
                throw  new RuntimeException("Корзина не существует");
            }

            List<Dish> dishes = cart.getDishes();
            if (dishes == null || dishes.isEmpty()) {
                throw new RuntimeException("Корзина пуста");
            }

            Dish dishToRemove = null;
            for (Dish dish : dishes) {
                if (dish.getId().equals(dishId)) {
                    dishToRemove = dish;
                    break;
                }
            }

            if (dishToRemove == null) {
                throw new RuntimeException("Блюдо не найдено в корзине");
            }

            dishes.remove(dishToRemove);
            cart.setDishes(dishes);
            cartRepository.save(cart);

            return "Блюдо удалено из корзины";
        }
    public List<CartItemDto> getAllDishesFromCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Cart cart = user.getCart();
        if (cart == null) {
            throw new CartNotFoundException("Корзина пользователне найдена");
        }

        List<Dish> dishes = cart.getDishes();
        List<CartItemDto> cartItems = new ArrayList<>();

        for (Dish dish : dishes) {
            CartItemDto cartItem = new CartItemDto();
            cartItem.setName(dish.getName());
            cartItem.setPrice(dish.getPrice());
            cartItems.add(cartItem);
        }

        return cartItems;
    }
}
