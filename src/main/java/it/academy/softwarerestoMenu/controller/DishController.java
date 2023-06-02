package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.DishDTO;
import it.academy.softwarerestoMenu.dto.DishDTOforFilter;
import it.academy.softwarerestoMenu.dto.DishResponseDTO;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.exceptions.DishNotFoundException;
import it.academy.softwarerestoMenu.mappers.DishMapper;
import it.academy.softwarerestoMenu.services.DishService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dishes")
@AllArgsConstructor
public class DishController {
    private final DishService dishService;
    private final DishMapper dishMapper;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public DishResponseDTO create(@RequestBody DishDTO dishDTO) {
        return dishService.save(dishDTO);
    }


    @GetMapping("/{id}")
    public DishResponseDTO getById(@PathVariable Long id) {
        DishResponseDTO dishResponseDTO = dishService.getById(id);
        return dishResponseDTO;
    }

    @GetMapping("/all")
    public List<DishResponseDTO> getAllDishes() {
        return dishService.findAll();
    }

    @GetMapping("/")
    public List<DishDTO> getAllPublishedDishes() {
        List<Dish> publishedDishes = dishService.getAllPublishedDishes();
        return publishedDishes.stream()
                .map(dishMapper::map)
                .collect(Collectors.toList());
    }

    @PutMapping("/{dishId}")
    public DishResponseDTO updateDish(@PathVariable Long dishId, @RequestBody DishDTO dishDTO) {
        return dishService.update(dishId, dishDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        dishService.delete(id);
    }

    @GetMapping("/published")
    public Map<String, List<DishDTOforFilter>> getPublishedDishesGroupedByCategory() {
        return dishService.getAllPublishedDishesGroupedByCategory();
    }

    @GetMapping("/filter")
    public List<DishDTO> getDishesByFilters
    (@RequestParam(value = "isVegan", required = false) boolean isVegan,
     @RequestParam(value = "isSpecial", required = false) boolean isSpecial) {
        List<Dish> dishes = dishService.getDishesByFilters(isVegan, isSpecial);
        return dishes.stream()
                .map(dishMapper::map)
                .collect(Collectors.toList());
    }
}