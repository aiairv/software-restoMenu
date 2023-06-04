package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.entity.Order;
import it.academy.softwarerestoMenu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUser(User user);
}