package it.academy.softwarerestoMenu.service;

import it.academy.softwarerestoMenu.dto.IngredientDto;
import it.academy.softwarerestoMenu.entity.Ingredient;
import it.academy.softwarerestoMenu.repository.IngredientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IngredientService {
    IngredientRepository ingredientRepository;

    //Возвращает список всех категорий блюд
    public List<IngredientDto> getAllIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        List<IngredientDto> dtos = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            dtos.add(entityToDto(ingredient));
        }
        return dtos;
    }

    //Возвращает информацию о конкретном ингредиенте по его ID
    public IngredientDto getIngredientById(Long id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isPresent()) {
            return entityToDto(ingredient.get());
        } else {
            return null;
        }
    }

    //Добавляет новый ингредиент в базу данных
    public IngredientDto addNewIngredient(IngredientDto ingredientDto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDto.getId());
        ingredient.setName(ingredientDto.getName());
        ingredient = ingredientRepository.save(ingredient);
        ingredientDto.setId(ingredient.getId());
        return ingredientDto;
    }

    //Изменяет информацию о конкретном ингредиенте по его ID
    public IngredientDto updateIngredient(Long id, IngredientDto dto) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isPresent()) {
            Ingredient updatedIngredient = ingredient.get();
            updatedIngredient.setName(dto.getName());
            ingredientRepository.save(updatedIngredient);
        } else {
            throw new EntityNotFoundException("Ingredient with id: " + id + " not found");
        }
        return dto;
    }

    //Удаляет ингредиент
    public String deleteIngredient(Long id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isPresent()) {
            Ingredient deletedIngredient = ingredient.get();
            ingredientRepository.delete(deletedIngredient);
        } else {
            throw new EntityNotFoundException("Ingredient with id: " + id + " not found");
        }
        return id + " ingredient is deleted";
    }

    public IngredientDto entityToDto(Ingredient ingredient){
        IngredientDto dto = new IngredientDto();
        dto.setId(ingredient.getId());
        dto.setName(ingredient.getName());
        return dto;
    }


}

