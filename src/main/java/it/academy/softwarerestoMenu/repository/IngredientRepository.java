package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByRemoveDateTimeIsNull();

    Optional<Ingredient> findByName(String name);
}
