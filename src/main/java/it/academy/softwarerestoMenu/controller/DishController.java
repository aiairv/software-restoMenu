package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.CategoryDTO;
import it.academy.softwarerestoMenu.services.DishService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

//    @GetMapping
//    public List<CategoryDTO> getAllDishesGroupedByCategory() {
//        return dishService.getAllDishesGroupedByCategory();
//    }

    // остальные методы
}