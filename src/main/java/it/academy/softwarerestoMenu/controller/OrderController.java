package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.OrderDTO;
import it.academy.softwarerestoMenu.entity.Order;

import it.academy.softwarerestoMenu.mappers.OrderMapper;
import it.academy.softwarerestoMenu.services.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO); // Преобразование OrderDTO в Order
        Order createdOrder = orderService.create(order);
        return orderMapper.toDTO(createdOrder);
    }

    @PostMapping("/{orderId}/dishes/{dishId}")
    public OrderDTO addDishToOrder(@PathVariable Long orderId, @PathVariable Long dishId) {
        Order order = orderService.addDishToOrder(orderId, dishId);
        return orderMapper.toDTO(order);
    }

    @GetMapping("/{orderId}")
    public OrderDTO getOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrder(orderId);
        return orderMapper.toDTO(order);
    }
}


