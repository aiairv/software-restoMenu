package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.CategoryDTO;
import it.academy.softwarerestoMenu.dto.DishDTO;
import it.academy.softwarerestoMenu.exceptions.CategoryNotFoundException;
import it.academy.softwarerestoMenu.exceptions.DishNotFoundException;
import it.academy.softwarerestoMenu.model.Category;
import it.academy.softwarerestoMenu.model.Dish;
import it.academy.softwarerestoMenu.services.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public DishDTO create(@RequestBody DishDTO dishDTO) {
        return dishService.save(dishDTO);
    }

    @GetMapping("/{id}")
    public Dish getById(@PathVariable Long id) {
        return dishService.getById(id);
    }


    @GetMapping("/")
    public List<Dish> getAll() {
        return dishService.findAll();
    }

    @PutMapping("/")
    public DishDTO update(@RequestBody Dish dishDTO) {
        if (dishDTO.getId() == null) throw new DishNotFoundException("DishController: update()  id is null");
        return dishService.save(dishDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { dishService.delete(id);
    }

    @GetMapping("/published")
    public Map<Category, List<Dish>> getPublishedDishesGroupedByCategory() {
        return dishService.getAllPublishedDishesGroupedByCategory();
    }


}