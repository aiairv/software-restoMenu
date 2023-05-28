package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.entity.Dish;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    //    @Query("SELECT d.category, d FROM Dish d GROUP BY d.category")
//    List<Map<String, Dish>> getAllDishesGroupedByCategory();
//    @Query("SELECT new it.academy.softwarerestoMenu.dto.CategoryDTO(d.category, COUNT(d)) FROM Dish d GROUP BY d.category")
//    List<CategoryDTO> getAllDishesGroupedByCategory();
    List<Dish> findByIsPublishTrue();

    List<Dish> findAllByRemoveDateTimeIsNull();


//    @Query("SELECT d FROM Dish d " +
//            "WHERE (:isVegan IS NULL OR d.isVegan = :isVegan) " +
//            "AND (:isSpecial IS NULL OR d.isSpecial = :isSpecial) " )
////            "or (:toppingNames IS NULL OR t.name IN :toppingNames)");
//    List<Dish> filterDishes(Boolean isVegan, Boolean isSpecial);
    @Query("SELECT d FROM Dish d WHERE d.isPublish = true AND" +
            " d.isVegan = :isVegan AND d.isSpecial = :isSpecial")
    List<Dish> findDishesByFilters(@Param("isVegan") boolean isVegan,
                                               @Param("isSpecial") boolean isSpecial);
}
