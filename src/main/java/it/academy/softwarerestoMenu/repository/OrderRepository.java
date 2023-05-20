package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}