package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.entity.Ingredient;
import it.academy.softwarerestoMenu.exceptions.IngredientNotFoundException;
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
        if (repository.findByName(ingredient.getName()).isPresent()) {
            throw new IngredientNotFoundException(String.format("Ингредиент с названием %s уже существует", ingredient.getName()));
        }
        return repository.save(ingredient);
    }

    public Ingredient getById(Long id) {
        Ingredient ingredient = repository.findById(id).orElseThrow(null);
        if (ingredient == null) {
            throw new IngredientNotFoundException(String.format("Ингредиент %s не найдено", ingredient.getName()));
        }
        if (ingredient.getRemoveDateTime() != null) {
            throw new IngredientNotFoundException(String.format("Ингредиент %s в списке удаленных", ingredient.getName()));
        }
        return ingredient;
    }

    public Long delete(Long id) {
        var ingredient = getById(id);
        ingredient.setRemoveDateTime(LocalDateTime.now());
        repository.save(ingredient);
        return ingredient.getId();
    }

    public List<Ingredient> findAll() {
        return repository.findAllByRemoveDateTimeIsNull();
    }

    public Ingredient restore(Long id) {
        Ingredient ingredient = repository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(String.format("Ингредиент с id %s не найден", id)));
        if (ingredient.getRemoveDateTime() == null) {
            throw new IngredientNotFoundException(String.format("Ингредиент с id %s не удален", id));
        }
        ingredient.setRemoveDateTime(null);
        return repository.save(ingredient);
    }
}

