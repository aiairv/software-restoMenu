package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.entity.Ingredient;
import it.academy.softwarerestoMenu.exceptions.CategoryNotFoundException;
import it.academy.softwarerestoMenu.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository repository;

    public Ingredient create(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    public Ingredient getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(String.format("Ingredient with id %s not found", id)));
    }

    public void delete(Long id) {
        var ingredient = getById(id);
        ingredient.setRemoveDateTime(LocalDateTime.now());
        create(ingredient);
    }

    public List<Ingredient> findAll() {
        return repository.findAllByRemoveDateTimeIsNull();
    }
}

