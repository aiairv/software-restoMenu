package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.dto.CategoryDTO;
import it.academy.softwarerestoMenu.model.Category;
import it.academy.softwarerestoMenu.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DishRepository extends JpaRepository <Dish,Long>{
//    @Query("SELECT d.category, d FROM Dish d GROUP BY d.category")
//    List<Map<String, Dish>> getAllDishesGroupedByCategory();
//    @Query("SELECT new it.academy.softwarerestoMenu.dto.CategoryDTO(d.category, COUNT(d)) FROM Dish d GROUP BY d.category")
//    List<CategoryDTO> getAllDishesGroupedByCategory();
    List<Dish> findByIsPublishTrue();

    List<Dish> findAllByRemoveDateTimeIsNull();
}
