package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.CategoryDTO;
import it.academy.softwarerestoMenu.dto.ToppingDTO;
import it.academy.softwarerestoMenu.entity.Topping;
import it.academy.softwarerestoMenu.exceptions.CategoryNotFoundException;
import it.academy.softwarerestoMenu.entity.Category;
import it.academy.softwarerestoMenu.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category save(Category category) {
        return repository.save(category);
    }

    public Category getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(String.format("Category with id %s not found", id)));
    }

    public void delete(Long id) {
        var category = getById(id);
        category.setRemoveDateTime(LocalDateTime.now());
        save(category);
    }

    public CategoryDTO mapToDto(Category category) {
     return   CategoryDTO.builder()
             .id(category.getId())
             .name(category.getName())
             .isPublish(category.getIsPublish())
             .build();
    }

    public List<Category> findAll() {
        return repository.findAllByRemoveDateTimeIsNull();
    }
}

