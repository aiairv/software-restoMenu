package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.DishDTOforFilter;
import it.academy.softwarerestoMenu.entity.Category;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.exceptions.DishNotFoundException;
import it.academy.softwarerestoMenu.mappers.DishMapper;
import it.academy.softwarerestoMenu.repository.CategoryRepository;
import it.academy.softwarerestoMenu.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

@Service
public class DishService {

    private final DishRepository repository;

    public DishService(DishRepository dishRepository, CategoryRepository categoryRepository, DishMapper dishMapper) {
        this.repository = dishRepository;
    }
    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    public Dish getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new DishNotFoundException((String.format("Category with id %s not found", id))));
    }

    public void delete(Long id) {
        var dish = getById(id);
        dish.setRemoveDateTime(LocalDateTime.now());
        save(dish);
    }

    public List<Dish> findAll() {
        return repository.findAllByRemoveDateTimeIsNull();
    }

    public List<Dish> getAllPublishedDishes() {
        List<Dish> alldish = repository.findAllByRemoveDateTimeIsNull();
        List<Dish> publishedDishes = alldish.stream()
                .filter(Dish::isPublish)
                .collect(Collectors.toList());
        return publishedDishes;
    }

    public Map<String, List<DishDTOforFilter>> getAllPublishedDishesGroupedByCategory() {
        List<Dish> publishedDishes = repository.findByIsPublishTrue();
        Map<Category, List<Dish>> dishesByCategory = publishedDishes.stream()
                .collect(Collectors.groupingBy(Dish::getCategory));

        Map<String, List<DishDTOforFilter>> getListMap = new HashMap<>();

        for (Map.Entry<Category, List<Dish>> entry : dishesByCategory.entrySet()) {
            Category category = entry.getKey();
            List<Dish> dishes = entry.getValue();

            List<DishDTOforFilter> dishDTOs = dishes.stream()
                    .map(d -> new DishDTOforFilter(d.getName(), d.getDescription(), d.getPrice()))
                    .collect(Collectors.toList());

            getListMap.put(category.getName(), dishDTOs);
        }

        return getListMap;
    }


//    public List<Dish> filterDishes(Boolean isVegan, Boolean isSpecial) {
////        return repository.filterDishes(isVegan, isSpecial, toppingNames);
//        return repository.filterDishes(isVegan, isSpecial);
//    }
    public List<Dish> getDishesByFilters(boolean isVegan, boolean isSpecial) {
    return repository.findDishesByFilters(isVegan, isSpecial);
}
}









