package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository <Dish,Long>{
//    @Query("SELECT d.category, d FROM Dish d GROUP BY d.category")
//    List<Map<String, Dish>> getAllDishesGroupedByCategory();
//    @Query("SELECT new it.academy.softwarerestoMenu.dto.CategoryDTO(d.category, COUNT(d)) FROM Dish d GROUP BY d.category")
//    List<CategoryDTO> getAllDishesGroupedByCategory();
    List<Dish> findByIsPublishTrue();

    List<Dish> findAllByRemoveDateTimeIsNull();
}
