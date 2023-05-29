package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.entity.Order;
import it.academy.softwarerestoMenu.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create/{cartId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long cartId) {
        Order order = orderService.createOrder(cartId);
        return ResponseEntity.ok(order);
    }

    // Другие методы контроллера для работы с заказами
}

