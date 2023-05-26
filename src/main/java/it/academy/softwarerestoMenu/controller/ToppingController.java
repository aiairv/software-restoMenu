package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.entity.Topping;
import it.academy.softwarerestoMenu.exceptions.ToppingNotFoundException;
import it.academy.softwarerestoMenu.services.ToppingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/toppings")
public class ToppingController {

    private final ToppingService service;

    public ToppingController(ToppingService service) {
        this.service = service;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Topping create(@RequestBody Topping topping) {
        return service.create(topping);
    }

    @GetMapping("/{id}")
    public Topping getById(@PathVariable Long id) {
        return service.getById(id);
    }


    @GetMapping("/")
    public List<Topping> getAll() {
        return service.findAll();
    }

    @PutMapping("/")
    public Topping update(@RequestBody Topping topping) {
        if (topping.getId() == null) throw new ToppingNotFoundException("DishController: update()  id is null");
        return service.create(topping);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}