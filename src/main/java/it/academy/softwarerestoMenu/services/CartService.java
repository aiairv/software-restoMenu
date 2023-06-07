package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.DishDtoForFilter;
import it.academy.softwarerestoMenu.dto.ResponseCartDto;
import it.academy.softwarerestoMenu.entity.Cart;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.entity.User;
import it.academy.softwarerestoMenu.enums.CartStatus;
import it.academy.softwarerestoMenu.exceptions.CartNotFoundException;

import it.academy.softwarerestoMenu.repository.CartRepository;
import it.academy.softwarerestoMenu.repository.DishRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private UserService userService;
    private DishRepository dishRepository;

    public Cart findById(Long id) {
        return cartRepository.findByIdAndRemoveDateTimeIsNull(id).orElseThrow(CartNotFoundException::new);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public ResponseCartDto addDishToCart(Long userId, Long dishId) {
        User user = userService.getUserById(userId);

        var cart = cartRepository.findByUserAndStatusAndRemoveDateTimeIsNull(user, CartStatus.NEW);

        Long cartId = null;

        if (cart.isPresent()) {
//            cartSave = cart.get();

            BigDecimal totalSum = cart.get().getTotal();
            Dish dish = dishRepository.getDishById(dishId);
            totalSum = totalSum.add(dish.getPrice());

            List<Dish> dishes = cart.get().getDishes();
            dishes.add(dish);
            cart.get().setDishes(dishes);
            cart.get().setUser(user);
            cart.get().setStatus(CartStatus.NEW);
            cart.get().setTotal(totalSum);

            cartId = cartRepository.save(cart.get()).getId();
        } else {
            var cartSave = new Cart();
            Dish dish = dishRepository.getDishById(dishId);
            List<Dish> dishes = new ArrayList<>();
            dishes.add(dish);
            cartSave.setUser(user);
            cartSave.setDishes(dishes);
            cartSave.setStatus(CartStatus.NEW);
            cartSave.setTotal(dish.getPrice());
            cartId = cartRepository.save(cartSave).getId();

        }

        return getAllDishesFromCart(cartId);
    }


    public ResponseCartDto deleteDishFromCart(Long userId, Long dishId) {
        User user = userService.getUserById(userId);

        var cart = cartRepository.findByUserAndStatusAndRemoveDateTimeIsNull(user, CartStatus.NEW);

        Long cartId = null;
        if (cart.isPresent()) {
//            cartSave = cart.get();


            List<Dish> dishes = cart.get().getDishes();

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
            cart.get().setDishes(dishes);

            cartId = cartRepository.save(cart.get()).getId();
        }

        return getAllDishesFromCart(cartId);
    }

    public ResponseCartDto getAllDishesFromCart(Long cartId) {
        var cart = cartRepository.findById(cartId);

        var cartDto = new ResponseCartDto();
        if (cart.isPresent()) {

            var list = cart.get().getDishes().stream()
                    .map(d -> new DishDtoForFilter(d.getName(), d.getDescription(), d.getPrice()))
                    .toList();
            cartDto.setDishDtoForFilters(list);
            cartDto.setTotal(cart.get().calculateTotalAmount());
            cartDto.setComment(cart.get().getComment());
            cartDto.setPlace(cart.get().getPlace());
        }
        return cartDto;
    }
}
