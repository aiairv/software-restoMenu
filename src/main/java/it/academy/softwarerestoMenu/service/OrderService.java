package it.academy.softwarerestoMenu.service;

import it.academy.softwarerestoMenu.dto.OrderDto;
import it.academy.softwarerestoMenu.entity.Cart;
import it.academy.softwarerestoMenu.entity.Order;
import it.academy.softwarerestoMenu.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    CartService cartService;
    OrderRepository orderRepository;

    public Order createOrder(Long cartId) {
        Cart cart = cartService.getCartById(cartId);

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setDishes(cart.getDishes());

        orderRepository.save(order);

        cartService.deleteCart(cartId);

        return order;
    }
    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setOrderTime(order.getOrderTime());

            return orderDto;
        }
        return null;
    }
}

