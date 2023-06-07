package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.DishDTO;
import it.academy.softwarerestoMenu.dto.DishDtoForFilter;
import it.academy.softwarerestoMenu.dto.DishResponseDTO;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.entity.ResponseMessage;
import it.academy.softwarerestoMenu.enums.ResultCode;
import it.academy.softwarerestoMenu.mappers.DishMapper;
import it.academy.softwarerestoMenu.services.DishService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dishes")
@AllArgsConstructor
@Slf4j
public class DishController {
    private final DishService dishService;
    private final DishMapper dishMapper;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage<DishResponseDTO> create(@RequestBody DishDTO dishDTO) {
        try {
            return new ResponseMessage<>(
                    dishService.save(dishDTO),
                    ResultCode.SUCCESS, "Блюдо успешно создано",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception exception) {
            log.error("DishController: create", exception);
            return new ResponseMessage<>(null, ResultCode.FAIL, exception.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }


    @GetMapping("/{id}")
    public ResponseMessage<DishResponseDTO> getById(@PathVariable Long id) {
        try {
            DishResponseDTO dishResponseDTO = dishService.getById(id);
            return new ResponseMessage<>(
                    dishResponseDTO,
                    ResultCode.SUCCESS, "Блюдо по ID найдено",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception exception) {
            log.error("DishController: getById", exception);
            return new ResponseMessage<>(null,
                    ResultCode.FAIL,
                    exception.getMessage(),
                    ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/all")
    public List<DishDtoForFilter> getAllDishes() {
        return dishService.findAll();
    }

    @GetMapping("/")
    public List<DishDTO> getAllPublishedDishes() {
        List<Dish> publishedDishes = dishService.getAllPublishedDishes();
        return publishedDishes.stream()
                .map(dishMapper::map)
                .collect(Collectors.toList());
    }

    @PutMapping("/{dishId}")
    public ResponseMessage<DishResponseDTO> updateDish(@PathVariable Long dishId, @RequestBody DishDTO dishDTO) {
        try {
            return new ResponseMessage<>(
                    dishService.update(dishId, dishDTO),
                    ResultCode.SUCCESS,
                    "Блюдо успешно обновлено",
                    ResultCode.SUCCESS.getHttpCode()
            );
        } catch (Exception exception) {
            log.error("DishController: updateDish", exception);
            return new ResponseMessage<>(
                    null,
                    ResultCode.FAIL,
                    exception.getMessage(),
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<Long> delete(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    dishService.delete(id),
                    ResultCode.SUCCESS, "Блюдо успешно удалено!",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("DishController: delete ", e);
            return new ResponseMessage<>(null, ResultCode.FAIL,
                    e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/published")
    public Map<String, List<DishDtoForFilter>> getPublishedDishesGroupedByCategory() {
        return dishService.getAllPublishedDishesGroupedByCategory();
    }

    @GetMapping("/filter")
    public ResponseMessage<List<DishDtoForFilter>> getDishesByFilters(
            @RequestParam(name = "isVegan", defaultValue = "false") boolean isVegan,
            @RequestParam(name = "isSpecial", defaultValue = "false") boolean isSpecial) {
        try {
            List<DishDtoForFilter> filteredDishes = dishService.getDishesByFilters(isVegan, isSpecial);
            if (filteredDishes.isEmpty()) {
                return new ResponseMessage<>(
                        null,
                        ResultCode.FAIL,
                        "По запросу ничего не найдено",
                        ResultCode.FAIL.getHttpCode()
                );
            } else {
                return new ResponseMessage<>(
                        filteredDishes,
                        ResultCode.SUCCESS,
                        "По запросу получены блюда",
                        ResultCode.SUCCESS.getHttpCode()
                );
            }
        } catch (Exception exception) {
            log.error("DishController: getDishesByFilters", exception);
            return new ResponseMessage<>(
                    null,
                    ResultCode.FAIL,
                    exception.getMessage(),
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
}
