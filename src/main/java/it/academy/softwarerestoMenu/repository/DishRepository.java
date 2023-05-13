package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository <Dish,Long>{
}
