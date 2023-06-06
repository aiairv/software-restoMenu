package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.OrderDTO;
import it.academy.softwarerestoMenu.dto.OrderResponseDTO;
import it.academy.softwarerestoMenu.entity.Cart;
import it.academy.softwarerestoMenu.entity.Order;
import it.academy.softwarerestoMenu.enums.CartStatus;
import it.academy.softwarerestoMenu.enums.OrderStatus;
import it.academy.softwarerestoMenu.enums.PaymentEnum;
import it.academy.softwarerestoMenu.enums.Place;
import it.academy.softwarerestoMenu.exceptions.OrderNotFoundException;
import it.academy.softwarerestoMenu.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private CartService cartService;
    private PaymentService paymentService;

    @Transactional
    public OrderResponseDTO createOrder(Long cartId, String comment, Place place) {
        Cart cart = cartService.findById(cartId);
        cart.setComment(comment);
        cart.setPlace(place);
        cart.setStatus(CartStatus.CLOSED);
        cart = cartService.save(cart);

        Order order = new Order();
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.NEW);
        order.setCart(cart);
        var saveOrder = orderRepository.save(order);
        return mapFromEntity(saveOrder.getId());
    }

    public OrderResponseDTO choicePayment(Long orderId, PaymentEnum paymentEnum) {
       var order = orderRepository.findByIdAndOrderStatus(orderId, OrderStatus.NEW).orElseThrow(OrderNotFoundException::new);
       order.setPaymentEnum(paymentEnum);

       if (PaymentEnum.CASH == paymentEnum) order.setOrderStatus(OrderStatus.PAID);
       else order.setOrderStatus(OrderStatus.PENDING);

       orderRepository.save(order);

        return mapFromEntity(order.getId());
    }

    public OrderResponseDTO payment(Long orderId, String cardNumber, String cvv, String expiredDate) {
        var order = orderRepository.findByIdAndOrderStatus(orderId, OrderStatus.PENDING).orElseThrow(OrderNotFoundException::new);
        order.setOrderStatus(OrderStatus.PAID);
        orderRepository.save(order);
        return mapFromEntity(order.getId());
    }



    OrderResponseDTO mapFromEntity(Long orderId) {
        var order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        var cart = order.getCart();
        return OrderResponseDTO.builder()
                .id(order.getId())
                .orderTime(order.getOrderTime())
                .orderStatus(order.getOrderStatus())
                .dishes(cartService.getAllDishesFromCart(cart.getId()))
                .build();
    }

    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
        return null;
    }


}





