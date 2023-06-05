package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.*;
import it.academy.softwarerestoMenu.entity.Category;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.entity.Ingredient;
import it.academy.softwarerestoMenu.entity.Topping;
import it.academy.softwarerestoMenu.exceptions.CategoryNotFoundException;
import it.academy.softwarerestoMenu.exceptions.DishNotFoundException;
import it.academy.softwarerestoMenu.mappers.DishMapper;
import it.academy.softwarerestoMenu.repository.CategoryRepository;
import it.academy.softwarerestoMenu.repository.DishRepository;
import it.academy.softwarerestoMenu.repository.IngredientRepository;
import it.academy.softwarerestoMenu.repository.ToppingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    private CategoryRepository categoryRepository;
    private IngredientRepository ingredientRepository;

    public DishResponseDTO save(DishDTO dishDTO) {
        Dish dish = dishMapper.map(dishDTO);

        Category category = categoryRepository.findById(dishDTO.getCategoryId()).orElse(null);
        List<Ingredient> ingredients = ingredientRepository.findAllById(dishDTO.getIngredientIds());

        dish.setCategory(category);
        dish.setIngredients(ingredients);

        Dish createdDish = dishRepository.save(dish);
        return mapperToDto(createdDish);
    }

    public DishResponseDTO mapperToDto(Dish dish) {

        return DishResponseDTO.builder()
                .id(dish.getId())
                .name(dish.getName())
                .description(dish.getDescription())
                .price(dish.getPrice())
                .isSpecial(dish.getIsSpecial())
                .isVegan(dish.getIsVegan())
                .isPublish(dish.getIsPublish())
                .category(new CategoryDTO().builder()
                        .id(dish.getCategory().getId())
                        .name(dish.getCategory().getName())
                        .isPublish(dish.getCategory().getIsPublish())
                        .build())
                .ingredients(convertIngredientToDTOList(dish.getIngredients()))
                .build();


    }

    public List<IngredientDTO> convertIngredientToDTOList(List<Ingredient> ingredients) {
        List<IngredientDTO> ingredientDTOs = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setName(ingredient.getName());
            ingredientDTOs.add(ingredientDTO);
        }
        return ingredientDTOs;
    }

    public List<ToppingDTO> convertToppingToDTOList(List<Topping> toppings) {
        List<ToppingDTO> toppingDTOs = new ArrayList<>();
        for (Topping topping : toppings) {
            ToppingDTO toppingDTO = new ToppingDTO();
            toppingDTO.setName(topping.getName());
            toppingDTOs.add(toppingDTO);
        }
        return toppingDTOs;
    }


    public DishResponseDTO getById(Long dishId) {
        Dish dish = dishRepository.findById(dishId).orElse(null);
        if (dish == null) {
            throw new DishNotFoundException(String.format("Блюдо %s не найдено",dish.getName()));
        }
        if (dish.getRemoveDateTime() != null){
            throw new DishNotFoundException(String.format("Блюдо %s в списке удаленных", dish.getName()));
        }
        return mapperToDto(dish);
    }

    public Long delete(Long id) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException("Блюдо не найдено"));
        dish.setRemoveDateTime(LocalDateTime.now());
        dishRepository.save(dish);
        return dish.getId();
    }

    public List<DishDTOforFilter> findAll() {
        List<Dish> dishes = dishRepository.findAllByRemoveDateTimeIsNull();
        return dishes.stream()
                .map(d -> new DishDTOforFilter(d.getName(), d.getDescription(), d.getPrice()))
                .collect(Collectors.toList());
    }

    public List<Dish> getAllPublishedDishes() {
        List<Dish> allDish = dishRepository.findAllByRemoveDateTimeIsNull();
        return allDish.stream()
                .filter(Dish::getIsPublish)
                .collect(Collectors.toList());
    }

    public Map<String, List<DishDTOforFilter>> getAllPublishedDishesGroupedByCategory() {
        List<Dish> publishedDishes = dishRepository.findByIsPublishTrue();
        Map<Category, List<Dish>> dishesByCategory = publishedDishes.stream()
                .collect(Collectors.groupingBy(Dish::getCategory));

        Map<String, List<DishDTOforFilter>> getListMap = new HashMap<>();

        for (Map.Entry<Category, List<Dish>> entry : dishesByCategory.entrySet()) {
            Category category = entry.getKey();
            List<Dish> dishes = entry.getValue();

            List<DishDTOforFilter> dishDTOs = dishes.stream()
                    .map(d -> new DishDTOforFilter(d.getName(), d.getDescription(), d.getPrice()))
                    .collect(Collectors.toList());

            getListMap.put(category.getName(), dishDTOs);
        }

        return getListMap;
    }

    public List<DishDTOforFilter> getDishesByFilters(boolean isVegan, boolean isSpecial) {
        List<Dish> dishes = dishRepository.findDishesByFilters(isVegan, isSpecial);

        return dishes.stream()
                .map(d -> new DishDTOforFilter(d.getName(), d.getDescription(), d.getPrice()))
                .collect(Collectors.toList());
    }
    public DishResponseDTO update(Long dishId, DishDTO dishDTO) {
        Dish existingDish = dishRepository.findById(dishId).orElseThrow(() -> new DishNotFoundException("Блюдо не найдено с ID: " + dishId));
        if (existingDish.getRemoveDateTime() != null) {
            {
                throw new DishNotFoundException(String.format("Блюдо %s в списке удаленных", existingDish.getName()));
            }
        }
        existingDish.setName(dishDTO.getName());
        existingDish.setDescription(dishDTO.getDescription());
        existingDish.setIsPublish(dishDTO.getIsPublish());
        existingDish.setIsVegan(dishDTO.getIsVegan());
        existingDish.setIsSpecial(dishDTO.getIsSpecial());
        existingDish.setPrice(dishDTO.getPrice());
        existingDish.setUpdateDateTime(LocalDateTime.now());

        Category category = categoryRepository.findById(dishDTO.getCategoryId()).orElse(null);
        List<Ingredient> ingredients = ingredientRepository.findAllById(dishDTO.getIngredientIds());

        existingDish.setCategory(category);
        existingDish.setIngredients(ingredients);

        Dish updatedDish = dishRepository.save(existingDish);
        return mapperToDto(updatedDish);
    }

}









