package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
