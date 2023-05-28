package it.academy.softwarerestoMenu.services;


import it.academy.softwarerestoMenu.entity.Topping;
import it.academy.softwarerestoMenu.exceptions.ToppingNotFoundException;
import it.academy.softwarerestoMenu.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToppingService {

    @Autowired
    private ToppingRepository repository;

    public Topping create(Topping topping) {
        return repository.save(topping);
    }

    public Topping getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ToppingNotFoundException(String.format("Topping with id %s not found", id)));
    }

    public void delete(Long id) {
        var topping = getById(id);
        create(topping);
    }

    public List<Topping> findAll() {
        return repository.findAll();
    }
}

