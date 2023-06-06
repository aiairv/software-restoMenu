package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.CategoryDTO;
import it.academy.softwarerestoMenu.exceptions.CategoryNotFoundException;
import it.academy.softwarerestoMenu.entity.Category;
import it.academy.softwarerestoMenu.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category save(Category category) {
        if (repository.findByName(category.getName()).isPresent()) {
            throw new CategoryNotFoundException(String.format("Категория с названием %s уже существует", category.getName()));
        }
        return repository.save(category);
    }
    public Category restoreCategory(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Категория с id %s не найден", id)));
        if (category.getRemoveDateTime() == null) {
            throw new CategoryNotFoundException(String.format("Категория с id %s не удалена", id));
        }
        category.setRemoveDateTime(null);
        return repository.save(category);
    }



    public Category getById(Long id) {
        return repository.findById(id)
                .filter(category -> category.getRemoveDateTime() == null)
                .orElseThrow(
                () -> new CategoryNotFoundException(String.format("Категория с id: %s не найдена", id)));
    }

    public Long delete(Long id) {
        var category = getById(id);
        category.setRemoveDateTime(LocalDateTime.now());
        repository.save(category);
        return category.getId();
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

