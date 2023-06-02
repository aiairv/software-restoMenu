package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.*;
import it.academy.softwarerestoMenu.entity.Category;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.entity.Ingredient;
import it.academy.softwarerestoMenu.entity.Topping;
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
    private ToppingRepository toppingRepository;

    public DishResponseDTO save(DishDTO dishDTO) {
        Dish dish = dishMapper.map(dishDTO);

        // Загрузка категории, ингредиентов и топпингов по их идентификаторам
        Category category = categoryRepository.findById(dishDTO.getCategoryId()).orElse(null);
        List<Ingredient> ingredients = ingredientRepository.findAllById(dishDTO.getIngredientIds());
        List<Topping> toppings = toppingRepository.findAllById(dishDTO.getToppingIds());

        // Связывание объектов с блюдом
        dish.setCategory(category);
        dish.setIngredients(ingredients);
        dish.setToppings(toppings);

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
                .toppings(convertToppingToDTOList(dish.getToppings()))
                .build();


    }

    public List<IngredientDTO> convertIngredientToDTOList(List<Ingredient> ingredients) {
        List<IngredientDTO> ingredientDTOs = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setId(ingredient.getId());
            ingredientDTO.setName(ingredient.getName());
            ingredientDTOs.add(ingredientDTO);
        }
        return ingredientDTOs;
    }

    public List<ToppingDTO> convertToppingToDTOList(List<Topping> toppings) {
        List<ToppingDTO> toppingDTOs = new ArrayList<>();
        for (Topping topping : toppings) {
            ToppingDTO toppingDTO = new ToppingDTO();
            toppingDTO.setId(topping.getId());
            toppingDTO.setName(topping.getName());
            toppingDTOs.add(toppingDTO);
        }
        return toppingDTOs;
    }


    public DishResponseDTO getById(Long dishId) {
        Dish dish = dishRepository.findById(dishId).orElse(null);
        if (dish == null) {
            throw new DishNotFoundException("Dish not found");
        }
        return mapperToDto(dish);
    }
    public void delete(Long id) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException("Dish not found"));
        dish.setRemoveDateTime(LocalDateTime.now());
        dishRepository.save(dish);
    }

    public List<DishResponseDTO> findAll() {
        List<Dish> dishes = dishRepository.findAllByRemoveDateTimeIsNull();
        return dishes.stream()
                .map(this::mapperToDto)
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

    public List<Dish> getDishesByFilters(boolean isVegan, boolean isSpecial) {
        return dishRepository.findDishesByFilters(isVegan, isSpecial);
    }
    public DishResponseDTO update(Long dishId, DishDTO dishDTO) {
        Dish existingDish = dishRepository.findById(dishId).orElseThrow(() -> new DishNotFoundException("Dish not found with ID: " + dishId));

        existingDish.setName(dishDTO.getName());
        existingDish.setPrice(dishDTO.getPrice());

        Category category = categoryRepository.findById(dishDTO.getCategoryId()).orElse(null);
        List<Ingredient> ingredients = ingredientRepository.findAllById(dishDTO.getIngredientIds());
        List<Topping> toppings = toppingRepository.findAllById(dishDTO.getToppingIds());

        existingDish.setCategory(category);
        existingDish.setIngredients(ingredients);
        existingDish.setToppings(toppings);

        Dish updatedDish = dishRepository.save(existingDish);
        return mapperToDto(updatedDish);
    }

}









