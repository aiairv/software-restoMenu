package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.DishDtoForFilter;
import it.academy.softwarerestoMenu.dto.ResponseCartDto;
import it.academy.softwarerestoMenu.entity.Cart;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.entity.Order;
import it.academy.softwarerestoMenu.entity.User;
import it.academy.softwarerestoMenu.enums.CartStatus;
import it.academy.softwarerestoMenu.enums.OrderStatus;
import it.academy.softwarerestoMenu.exceptions.CartNotFoundException;
import it.academy.softwarerestoMenu.repository.CartRepository;
import it.academy.softwarerestoMenu.repository.DishRepository;
import it.academy.softwarerestoMenu.repository.OrderRepository;
import it.academy.softwarerestoMenu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class JobService {
    private OrderRepository orderRepository;

    @Scheduled(fixedDelay = 120000)
    public void processPaidOrders() {
        List<Order> paidOrders = orderRepository.findByOrderStatus(OrderStatus.PAID);
        for (Order order : paidOrders) {
            order.setOrderStatus(OrderStatus.PROCESSING);
            orderRepository.save(order);
        }
    }

    @Scheduled(fixedDelay = 60000)
    public void processProcessingOrders() {
        List<Order> paidOrders = orderRepository.findByOrderStatus(OrderStatus.PROCESSING);
        for (Order order : paidOrders) {
            order.setOrderStatus(OrderStatus.COMPLETED);
            orderRepository.save(order);
        }
    }
}
