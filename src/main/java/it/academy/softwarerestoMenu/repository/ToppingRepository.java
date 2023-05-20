package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.model.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<Topping,Long> {
}
