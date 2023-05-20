package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Long> {
}
