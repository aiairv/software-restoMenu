package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.OrderDTO;
import it.academy.softwarerestoMenu.dto.OrderRequestDTO;
import it.academy.softwarerestoMenu.dto.OrderResponseDTO;
import it.academy.softwarerestoMenu.enums.PaymentEnum;
import it.academy.softwarerestoMenu.enums.Place;
import it.academy.softwarerestoMenu.exceptions.CartNotFoundException;
import it.academy.softwarerestoMenu.exceptions.OrderNotFoundException;
import it.academy.softwarerestoMenu.exceptions.UserNotFoundException;
import it.academy.softwarerestoMenu.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestParam Long cartId,
                                                        @RequestParam(required = false) String comment,
                                                        @RequestParam Place place) {
        try {
            return ResponseEntity.ok(orderService.createOrder(cartId, comment, place));
        } catch (UserNotFoundException | CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/choicePayment")
    public ResponseEntity<OrderResponseDTO> choicePayment(@RequestParam Long orderId, @RequestParam PaymentEnum paymentEnum) {
        try {
            return ResponseEntity.ok(orderService.choicePayment(orderId, paymentEnum));
        } catch (UserNotFoundException | CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<OrderResponseDTO> payment(@RequestParam Long orderId,
                                                    @RequestParam String cardNumber,
                                                    @RequestParam String cvv,
                                                    @RequestParam String expiredDate) {
        try {
            return ResponseEntity.ok(orderService.payment(orderId, cardNumber, cvv, expiredDate));
        } catch (UserNotFoundException | CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @PostMapping("/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@RequestParam Long orderId) {
        try {
            return ResponseEntity.ok(orderService.cancelOrder(orderId));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/")
    public ResponseEntity<OrderResponseDTO> getOrderById(@RequestParam Long orderId) {
        try {
            return ResponseEntity.ok(orderService.getOrderById(orderId));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}




