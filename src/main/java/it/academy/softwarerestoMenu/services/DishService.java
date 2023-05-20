package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.CategoryDTO;
import it.academy.softwarerestoMenu.mapper.CategoryMapper;
import it.academy.softwarerestoMenu.mapper.DishMapper;
import it.academy.softwarerestoMenu.model.Category;
import it.academy.softwarerestoMenu.model.Dish;
import it.academy.softwarerestoMenu.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class  DishService {

    private final DishRepository dishRepository;
    private final CategoryMapper categoryMapper;

    public DishService(DishRepository dishRepository, CategoryMapper categoryMapper) {
        this.dishRepository = dishRepository;
        this.categoryMapper = categoryMapper;
    }



   /* public List<CategoryDTO> getAllDishesGroupedByCategory() {
        List<Dish> dishes = dishRepository.findByIsPublishTrue();
        Map<Category, List<Dish>> dishesByCategory = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getCategory));
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        dishesByCategory.forEach((category, dishesInCategory) -> {
            CategoryDTO categoryDTO = categoryMapper.convertToDTO(category);
            categoryDTO.setDish((Dish) dishesInCategory.stream().map(DishMapper:: convertToDTO).collect(Collectors.toList()));
            categoriesDTO.add(categoryDTO);
        });
        return categoriesDTO;
    }*/


}

