package it.academy.softwarerestoMenu.service;

import it.academy.softwarerestoMenu.entity.Category;
import it.academy.softwarerestoMenu.exceptions.CategoryNotFoundException;
import it.academy.softwarerestoMenu.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService{
//    CategoryRepository categoryRepository;
//
//    //Удаляет категорию из базы данных по её ID
//    public String deleteCategory(Long id){
//        Optional<Category> category = categoryRepository.findById(id);
//        if(category.isPresent()){
//            Category deletedCategory = category.get();
//            categoryRepository.delete(deletedCategory);
//        } else {
//        throw new EntityNotFoundException("Category with id: " + id + " not found");
//    }
//        return id + " category is deleted";
//}
//    }
    @Autowired
    private CategoryRepository repo;

    public Category save(Category category) {
        return repo.save(category);
    }

    public Category getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(String.format("Category with id %s not found", id)));
    }

    public void delete(Long id) {
        var category = getById(id);
        category.setRemoveDateTime(LocalDateTime.now());
        save(category);
    }

    public List<Category> findAll() {
        return repo.findAllByRemoveDateTimeIsNull();
    }
}
