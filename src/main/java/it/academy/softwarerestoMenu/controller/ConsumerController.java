package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.model.Consumer;
import it.academy.softwarerestoMenu.services.ConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consumers")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Consumer createUser(@RequestBody Consumer consumer) {
        return consumerService.createUser(consumer);
    }

    @GetMapping("/{id}")
    public Consumer getUserById(@PathVariable Long id) {
        return consumerService.getUserById(id);
    }

    @PutMapping("/{id}")
    public Consumer updateUser(@PathVariable Long id, @RequestBody Consumer user) {
        if (!id.equals(user.getId())) {
            throw new IllegalArgumentException("ID mismatch");
        }
        return consumerService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        consumerService.deleteUser(id);
    }
}
