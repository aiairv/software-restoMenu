package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByIsPublishTrue();

    List<Dish> findAllByRemoveDateTimeIsNull();

    Dish getDishById(Long id);

    @Query("SELECT d FROM Dish d WHERE d.isPublish = true AND" +
            " d.isVegan = :isVegan AND d.isSpecial = :isSpecial")
    List<Dish> findDishesByFilters(@Param("isVegan") boolean isVegan,
                                   @Param("isSpecial") boolean isSpecial);

    Optional<Dish> findByName(String name);
}
