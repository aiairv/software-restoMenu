package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.exceptions.CategoryNotFoundException;
import it.academy.softwarerestoMenu.model.Category;
import it.academy.softwarerestoMenu.model.Ingredient;
import it.academy.softwarerestoMenu.services.CategoryService;
import it.academy.softwarerestoMenu.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient create(@RequestBody Ingredient ingredient) {
        return service.create(ingredient);
    }

    @GetMapping("/{id}")
    public Ingredient getById(@PathVariable Long id) {
        return service.getById(id);
    }


    @GetMapping("/")
    public List<Ingredient> getAll() {
        return service.findAll();
    }

    @PutMapping("/")
    public Ingredient update(@RequestBody Ingredient ingredient) {
        if (ingredient.getId() == null) throw new CategoryNotFoundException("DishController: update()  id is null");
        return service.create(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        service.delete(id);
    }
}