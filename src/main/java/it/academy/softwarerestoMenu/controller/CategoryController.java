package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.entity.Category;
import it.academy.softwarerestoMenu.exceptions.CategoryNotFoundException;
import it.academy.softwarerestoMenu.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
//    CategoryService categoryService;

//    @DeleteMapping("/deleteCategory/{id}")
//    public String deleteCategory(@PathVariable Long id) {
//        return categoryService.deleteCategory(id);
//    }
//}
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody Category category) {
        return service.save(category);
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return service.getById(id);
    }


    @GetMapping("/")
    public List<Category> getAll() {
        return service.findAll();
    }

    @PutMapping("/")
    public Category update(@RequestBody Category category) {
        if (category.getId() == null) throw new CategoryNotFoundException("DishController: update()  id is null");
        return service.save(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        service.delete(id);
    }
}