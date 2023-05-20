package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository <Dish,Long>{

    List<Dish> findByIsPublishTrue();
}
