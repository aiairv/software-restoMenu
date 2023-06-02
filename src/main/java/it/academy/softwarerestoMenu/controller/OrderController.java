package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.OrderDTO;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.entity.Order;
import it.academy.softwarerestoMenu.entity.Topping;
import it.academy.softwarerestoMenu.entity.UpdateOrderStatusRequest;
import it.academy.softwarerestoMenu.enums.OrderStatus;
import it.academy.softwarerestoMenu.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @PostMapping("/{orderId}/dishes")
    public void addDishToOrder(@PathVariable Long orderId, @RequestBody Dish dish) {
        Order order = orderService.getById(orderId);
        orderService.addDishToOrder(order, dish);
    }

    @DeleteMapping("/{orderId}/dishes")
    public void removeDishFromOrder(@PathVariable Long orderId, @RequestBody Dish dish) {
        Order order = orderService.getById(orderId);
        orderService.removeDishFromOrder(order, dish);
    }

    @PostMapping("/{orderId}/toppings")
    public void addToppingToOrder(@PathVariable Long orderId, @RequestBody Topping topping) {
        Order order = orderService.getById(orderId);
        orderService.addToppingToOrder(order, topping);
    }

    @DeleteMapping("/{orderId}/toppings")
    public void removeToppingFromOrder(@PathVariable Long orderId, @RequestBody Topping topping) {
        Order order = orderService.getById(orderId);
        orderService.removeToppingFromOrder(order, topping);
    }

    @PutMapping("/{orderId}/status")
    public void updateOrderStatus(@PathVariable Long orderId, @RequestBody UpdateOrderStatusRequest request) {
        Order order = orderService.getById(orderId);
        OrderStatus status = request.getStatus();
        orderService.updateOrderStatus(order, status);
    }

    @GetMapping("/{orderId}/totalCost")
    public BigDecimal calculateTotalOrderCost(@PathVariable Long orderId) {
        Order order = orderService.getById(orderId);
        return orderService.calculateTotalOrderCost(order);
    }
}



