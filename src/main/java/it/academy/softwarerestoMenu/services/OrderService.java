package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.entity.Order;
import it.academy.softwarerestoMenu.exceptions.DishNotFoundException;
import it.academy.softwarerestoMenu.exceptions.OrderNotFoundException;
import it.academy.softwarerestoMenu.repository.DishRepository;
import it.academy.softwarerestoMenu.repository.OrderRepository;
import org.springframework.stereotype.Service;



// OrderService.java
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;

    public OrderService(OrderRepository orderRepository, DishRepository dishRepository) {
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;
    }

    public Order create() {
        Order order = new Order();
        return orderRepository.save(order);
    }

    public Order addDishToOrder(Long orderId, Long dishId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new DishNotFoundException("Dish not found"));

        order.getDishes().add(dish);
        return orderRepository.save(order);
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }
}
