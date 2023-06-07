package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.entity.Category;
import it.academy.softwarerestoMenu.entity.ResponseMessage;
import it.academy.softwarerestoMenu.enums.ResultCode;
import it.academy.softwarerestoMenu.exceptions.CategoryNotFoundException;
import it.academy.softwarerestoMenu.entity.Ingredient;
import it.academy.softwarerestoMenu.exceptions.IngredientNotFoundException;
import it.academy.softwarerestoMenu.services.IngredientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService service;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage<Ingredient> create(@RequestBody Ingredient ingredient) {
        try {
            return new ResponseMessage<>(
                    service.create(ingredient),
                    ResultCode.SUCCESS,
                    "Ингредиент успешно создан",
                    ResultCode.SUCCESS.getHttpCode()
            );
        } catch (Exception exception) {
            log.error("IngredientController: create", exception);
            return new ResponseMessage<>(
                    null,
                    ResultCode.FAIL,
                    exception.getMessage(),
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseMessage<Ingredient> getById(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    service.getById(id),
                    ResultCode.SUCCESS,
                    "Ингредиент по ID найден",
                    ResultCode.SUCCESS.getHttpCode()
            );
        } catch (Exception exception) {
            log.error("IngredientController: getById", exception);
            return new ResponseMessage<>(
                    null,
                    ResultCode.FAIL,
                    exception.getMessage(),
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @PostMapping("/{id}/restore")
    public ResponseMessage<Ingredient> restore(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    service.restore(id),
                    ResultCode.SUCCESS,
                    "Ингредиент успешно восстановлен!",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("IngredientController: restore ", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/")
    public List<Ingredient> getAll() {
        return service.findAll();
    }

    @PutMapping("/")
    public ResponseMessage<Ingredient> update(@RequestBody Ingredient ingredient) {
        if (ingredient.getId() == null)
            throw new IngredientNotFoundException("IngredientController: update()  id is null");
        try {
            return new ResponseMessage<>(
                    service.create(ingredient),
                    ResultCode.SUCCESS, "Ингредиент успешно обновлен!",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception exception) {
            log.error("IngredientController: update", exception);
            return new ResponseMessage<>(null, ResultCode.FAIL, exception.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<Long> delete(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    service.delete(id),
                    ResultCode.SUCCESS, "Ингредиент успешно удален!",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("IngredientController: delete ", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}