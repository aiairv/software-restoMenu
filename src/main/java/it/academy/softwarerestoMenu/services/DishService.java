package it.academy.softwarerestoMenu.services;


import it.academy.softwarerestoMenu.dto.DishDTO;
import it.academy.softwarerestoMenu.exceptions.CategoryNotFoundException;
import it.academy.softwarerestoMenu.model.Category;
import it.academy.softwarerestoMenu.model.Dish;
import it.academy.softwarerestoMenu.model.Ingredient;
import it.academy.softwarerestoMenu.model.Topping;
import it.academy.softwarerestoMenu.repository.CategoryRepository;
import it.academy.softwarerestoMenu.repository.DishRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class  DishService {

    private  DishRepository repository;
    private  CategoryRepository categoryRepository;


    public DishService(DishRepository dishRepository, CategoryRepository categoryRepository) {
        this.repository = dishRepository;
        this.categoryRepository = categoryRepository;
    }


    @Transactional
    public DishDTO save(String name, String description, BigDecimal price, boolean special,
                        boolean vegan, boolean publish, Category category,
                        List<Ingredient> ingredients, List<Topping> toppings) throws Exception{
        Dish dish = new Dish(name,description,price,special,vegan,publish,category,ingredients,toppings);
        repository.save(dish);
        return new DishDTO(
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.isSpecial(),
                dish.isVegan(),
                dish.isPublish(),
                dish.getCategory(),
                dish.getIngredients(),
                dish.getToppings()
        );
    }


    public Dish getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(String.format("Category with id %s not found", id)));
    }

    public void delete(Long id) {
        var dish = getById(id);
        dish.setRemoveDateTime(LocalDateTime.now());
        save(dish);
    }

    public List<Dish> findAll() {
        return repository.findAllByRemoveDateTimeIsNull();
    }
    public List<Dish> getAllPublishedDishes(){
        List<Dish> alldish = repository.findAllByRemoveDateTimeIsNull();
        List<Dish> publishedDishes = alldish.stream()
                .filter(Dish::isPublish)
                .collect(Collectors.toList());
        return  publishedDishes;
    }
    public Map<Category,List<Dish>> getAllPublishedDishesGroupedByCategory(){
        List<Dish> publishedDishes = repository.findByIsPublishTrue();
        Map<Category , List<Dish>> dishesByCategory = publishedDishes.stream()
                .collect(Collectors.groupingBy(Dish::getCategory));
        return dishesByCategory;
    }



}







