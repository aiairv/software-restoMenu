package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.entity.Order;
import it.academy.softwarerestoMenu.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.repository = orderRepository;
    }

    public Order create(Order order) {
        return repository.save(order);
    }

    public Order getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Order> getAll() {
        return repository.findAll();
    }

    public Order update(Order order) {
        return repository.save(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
