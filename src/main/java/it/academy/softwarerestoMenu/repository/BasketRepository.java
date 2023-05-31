package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Cart,Long> {
}
