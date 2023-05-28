package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.DishDTO;
import it.academy.softwarerestoMenu.dto.DishDTOforFilter;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.exceptions.DishNotFoundException;
import it.academy.softwarerestoMenu.mappers.DishMapper;
import it.academy.softwarerestoMenu.services.DishService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public DishDTO create(@RequestBody DishDTO dishDTO) {
        Dish dish = dishMapper.map(dishDTO);
        Dish createdDish = dishService.save(dish);
        return dishMapper.map(createdDish);
    }

    @GetMapping("/{id}")
    public DishDTO getById(@PathVariable Long id) {
        return dishMapper.map(dishService.getById(id));
    }

    @GetMapping("/all")
    public List<DishDTO> getAll() {
        List<Dish> dishes = dishService.findAll();
        return dishes.stream().map(dishMapper::map).collect(Collectors.toList());
    }

    @GetMapping("/")
    public List<DishDTO> getAllPublishedDishes() {
        List<Dish> publishedDishes = dishService.getAllPublishedDishes();
        return publishedDishes.stream()
                .map(dishMapper::map)
                .collect(Collectors.toList());
    }

    @PutMapping("/")
    public Dish update(@RequestBody Dish dish) {
        if (dish.getId() == null) throw new DishNotFoundException("DishController: update()  id is null");
        return dishService.save(dish);
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

    //    @GetMapping("/filter")
//    public List<Dish> filterDishes(
//            @RequestParam(required = false) Boolean isVegan,
//            @RequestParam(required = false) Boolean isSpecial
////            @RequestParam(required = false) List<String> toppingNames
//    ) {
//        return dishService.filterDishes(isVegan, isSpecial);
//    }
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