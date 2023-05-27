package it.academy.softwarerestoMenu.services;


import it.academy.softwarerestoMenu.dto.DishDTOFilter;
import it.academy.softwarerestoMenu.entity.Category;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.exceptions.DishNotFoundException;
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
    private CategoryRepository categoryRepository;


    public DishService(DishRepository dishRepository, CategoryRepository categoryRepository) {
        this.repository = dishRepository;
        this.categoryRepository = categoryRepository;
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

public Map<String, List<DishDTOFilter>> getAllPublishedDishesGroupedByCategory() {
    List<Dish> publishedDishes = repository.findByIsPublishTrue();
    Map<Category, List<Dish>> dishesByCategory = publishedDishes.stream()
            .collect(Collectors.groupingBy(Dish::getCategory));

    Map<String, List<DishDTOFilter>> getListMap = new HashMap<>();

    for (Map.Entry<Category, List<Dish>> entry : dishesByCategory.entrySet()) {
        Category category = entry.getKey();
        List<Dish> dishes = entry.getValue();

        List<DishDTOFilter> dishDTOs = dishes.stream()
                .map(d -> new DishDTOFilter( d.getName(), d.getDescription(), d.getPrice()))
                .collect(Collectors.toList());

        getListMap.put(category.getName(), dishDTOs);
    }

    return getListMap;
}

    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    public List<Dish> filterDishes(Boolean isVegan, Boolean isSpecial, List<String> toppingNames) {
//        return repository.filterDishes(isVegan, isSpecial, toppingNames);
        return repository.filterDishes(isVegan, isSpecial);


    }
}







