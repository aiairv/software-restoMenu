package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.DishDTO;
import it.academy.softwarerestoMenu.dto.DishDTOforFilter;
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

//    public Dish save(Dish dish) {
//        return repository.save(dish);
//    }
    public Dish save(DishDTO dishDTO) {
    Dish dish = dishMapper.map(dishDTO);

    // Загрузка категории, ингредиентов и топпингов по их идентификаторам
    Category category = categoryRepository.findById(dishDTO.getCategoryId()).orElse(null);
    List<Ingredient> ingredients = ingredientRepository.findAllById(dishDTO.getIngredientIds());
    List<Topping> toppings = toppingRepository.findAllById(dishDTO.getToppingIds());

    // Связывание объектов с блюдом
    dish.setCategory(category);
    dish.setIngredients(ingredients);
    dish.setToppings(toppings);

    return dishRepository.save(dish);
}


    public Dish getById(Long id) {
        return dishRepository.findById(id).orElseThrow(
                () -> new DishNotFoundException((String.format("Dish with id %s not found", id))));
    }

    public void delete(Long id) {
        Dish dish = getById(id);
        dish.setRemoveDateTime(LocalDateTime.now());
        dishRepository.save(dish);
    }
    public List<Dish> findAll() {
        return dishRepository.findAllByRemoveDateTimeIsNull();
    }

    public List<Dish> getAllPublishedDishes() {
        List<Dish> alldish = dishRepository.findAllByRemoveDateTimeIsNull();
        List<Dish> publishedDishes = alldish.stream()
                .filter(Dish::isPublish)
                .collect(Collectors.toList());
        return publishedDishes;
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
}









