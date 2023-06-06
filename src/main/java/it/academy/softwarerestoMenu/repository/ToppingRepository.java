package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.entity.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long> {
    List<Topping> findAllByRemoveDateTimeIsNull();

    Optional<Object> findByName(String name);
}