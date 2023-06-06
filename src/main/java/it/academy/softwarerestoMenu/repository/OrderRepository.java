package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.entity.Order;
import it.academy.softwarerestoMenu.entity.User;
import it.academy.softwarerestoMenu.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByIdAndOrderStatus(Long id, OrderStatus status);

    List<Order> findByOrderStatus(OrderStatus paid);
}