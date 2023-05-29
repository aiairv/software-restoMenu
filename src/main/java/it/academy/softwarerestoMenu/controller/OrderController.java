package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.OrderDto;
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
    @GetMapping("/getById/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        OrderDto orderDto = orderService.getOrderById(id);
        if (orderDto != null) {
            return ResponseEntity.ok(orderDto);
        }
        return ResponseEntity.notFound().build();
    }

}

