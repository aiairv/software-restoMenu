package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.OrderDTO;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.entity.Order;
import it.academy.softwarerestoMenu.entity.Topping;
import it.academy.softwarerestoMenu.enums.OrderStatus;
import it.academy.softwarerestoMenu.exceptions.OrderNotFoundException;
import it.academy.softwarerestoMenu.exceptions.ToppingNotFoundException;
import it.academy.softwarerestoMenu.repository.DishRepository;
import it.academy.softwarerestoMenu.repository.OrderRepository;
import it.academy.softwarerestoMenu.repository.ToppingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private DishService dishService;
    private DishRepository dishRepository;
    private ToppingRepository toppingRepository;

    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.CONFIRMED);
        order.setDishes(convertDishIds(orderDTO.getDishes()));
        order.setToppings(convertToppingIds(orderDTO.getToppings()));
        order.setUser(orderDTO.getUser());
        return orderRepository.save(order);
    }
    public void addDishToOrder(Order order, Dish dish) {
        List<Dish> dishes = order.getDishes();
        dishes.add(dish);
        order.setDishes(dishes);
        orderRepository.save(order);
    }
    public void removeDishFromOrder(Order order, Dish dish) {
        List<Dish> dishes = order.getDishes();
        dishes.remove(dish);
        order.setDishes(dishes);
        orderRepository.save(order);
    }
    public void addToppingToOrder(Order order, Topping topping) {
        List<Topping> toppings = order.getToppings();
        toppings.add(topping);
        order.setToppings(toppings);
        orderRepository.save(order);
    }
    public void removeToppingFromOrder(Order order, Topping topping) {
        List<Topping> toppings = order.getToppings();
        toppings.remove(topping);
        order.setToppings(toppings);
        orderRepository.save(order);
    }
    public void updateOrderStatus(Order order, OrderStatus status) {
        order.setOrderStatus(status);
        orderRepository.save(order);
    }
    public BigDecimal calculateTotalOrderCost(Order order) {
        BigDecimal totalCost = BigDecimal.ZERO;
        List<Dish> dishes = order.getDishes();
        List<Topping> toppings = order.getToppings();

        for (Dish dish : dishes) {
            totalCost = totalCost.add(dish.getPrice());
        }

        for (Topping topping : toppings) {
            totalCost = totalCost.add(topping.getPrice());
        }

        return totalCost;
    }
    private List<Dish> convertDishIds(List<Long> dishIds) {
        List<Dish> dishes = new ArrayList<>();

        for (Long dishId : dishIds) {
            Dish dish = dishRepository.getById(dishId);

            if (dish != null) {
                dishes.add(dish);
            }
        }
        return dishes;
    }
    private List<Topping> convertToppingIds(List<Long> toppingIds) {
        List<Topping> toppings = new ArrayList<>();

        for (Long toppingId : toppingIds) {
            Optional<Topping> optionalTopping = toppingRepository.findById(toppingId);

            if (optionalTopping.isPresent()) {
                toppings.add(optionalTopping.get());
            } else {
                // Handle the case when the topping is not found, such as throwing an exception or skipping the topping
                throw new ToppingNotFoundException("Topping not found with ID: " + toppingId);
            }
        }
        return toppings;
    }
    public Order getById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            throw new OrderNotFoundException("Order not found with ID: " + orderId);
        }
    }

}




