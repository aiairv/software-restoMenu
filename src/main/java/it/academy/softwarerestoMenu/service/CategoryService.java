package it.academy.softwarerestoMenu.service;

import it.academy.softwarerestoMenu.entity.Category;
import it.academy.softwarerestoMenu.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService{
    CategoryRepository categoryRepository;

    //Удаляет категорию из базы данных по её ID
    public String deleteCategory(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            Category deletedCategory = category.get();
            categoryRepository.delete(deletedCategory);
        } else {
        throw new EntityNotFoundException("Category with id: " + id + " not found");
    }
        return id + " category is deleted";
}
    }
