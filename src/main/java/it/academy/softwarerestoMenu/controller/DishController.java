package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.exceptions.DishNotFoundException;
import it.academy.softwarerestoMenu.entity.Category;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.service.DishService;
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
    public Dish create(@RequestBody Dish dish) {
        return dishService.save(dish);
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
    public Dish update(@RequestBody Dish dish) {
        if (dish.getId() == null) throw new DishNotFoundException("DishController: update()  id is null");
        return dishService.save(dish);
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