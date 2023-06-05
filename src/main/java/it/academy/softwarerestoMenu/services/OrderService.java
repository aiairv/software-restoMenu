package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.OrderDTO;
import it.academy.softwarerestoMenu.dto.OrderRequestDTO;
import it.academy.softwarerestoMenu.dto.PaymentDTO;
import it.academy.softwarerestoMenu.dto.ToppingDTO;
import it.academy.softwarerestoMenu.entity.*;
import it.academy.softwarerestoMenu.exceptions.CartNotFoundException;
import it.academy.softwarerestoMenu.exceptions.OrderNotFoundException;
import it.academy.softwarerestoMenu.exceptions.UserNotFoundException;
import it.academy.softwarerestoMenu.mappers.OrderMapper;
import it.academy.softwarerestoMenu.repository.CartRepository;
import it.academy.softwarerestoMenu.repository.OrderRepository;
import it.academy.softwarerestoMenu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private PaymentService paymentService;
    private OrderMapper orderMapper;

    public void createOrder(OrderRequestDTO orderRequestDTO) {
        User user = userRepository.findById(orderRequestDTO.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow();

/*        Order order = new Order();
        order.setUser(user);
        order.setDishes(cart.getDishes());
        order.setToppings(cart.getToppings());

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrderId(order.getId());
        paymentDTO.setAmount(order.getTotalAmount());
        paymentDTO.setPaymentMethod(orderRequestDTO.getPaymentMethod());

        paymentService.processPayment(paymentDTO);*/

//        orderRepository.save(order);
    }

    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);

//        return orderMapper.mapperToDto(order);
        return null;
    }

    public OrderDTO mapperToDto(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .orderStatus(order.getOrderStatus())
//                .dishes(order.getOrderItems())
                .build();
    }
    public List<OrderDTO> convertToppingToDTOList(List<Order> orders) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order: orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setUser(order.getUser());
//            orderDTO.getOrderStatus(order.getOrderStatus())
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }
}





