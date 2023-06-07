package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.entity.Category;
import it.academy.softwarerestoMenu.entity.ResponseMessage;
import it.academy.softwarerestoMenu.enums.ResultCode;
import it.academy.softwarerestoMenu.exceptions.CategoryNotFoundException;
import it.academy.softwarerestoMenu.services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@Slf4j
@AllArgsConstructor
public class CategoryController {

    private final CategoryService service;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseMessage<Category> create(@RequestBody Category category) {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            return new ResponseMessage<>(service.save(category), ResultCode.SUCCESS, "Категория успешно создана", ResultCode.SUCCESS.getHttpCode());
        } catch (AuthenticationException exception) {
            log.error("CategoryController: create ", exception);
            return new ResponseMessage<>(null, ResultCode.FAIL, exception.getMessage(), ResultCode.FAIL.getHttpCode());
        } catch (Exception exception) {
            log.error("CategoryController: create ", exception);
            return new ResponseMessage<>(null, ResultCode.FAIL, exception.getMessage(), ResultCode.FAIL.getHttpCode());
        }


    }

    @PostMapping("/{id}/restore")
    public ResponseMessage<Category> restoreCategory(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(service.restoreCategory(id), ResultCode.SUCCESS, "Категория успешно восстановлена!", ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CategoryController: restoreCategory ", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }


    @GetMapping("/{id}")
    public ResponseMessage<Category> getById(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(service.getById(id), ResultCode.SUCCESS, "Категория по ID найдена", ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CategoryController: getById ", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/all")
    public List<Category> getAll() {
        return service.findAll();
    }

    @PutMapping("/update")
    public ResponseMessage<Category> update(@RequestBody Category category) {
        if (category.getId() == null) throw new CategoryNotFoundException("CategoryController: update()  id is null");
        try {
            return new ResponseMessage<>(service.save(category), ResultCode.SUCCESS, "Категория успешно обновлена!", ResultCode.SUCCESS.getHttpCode());
        } catch (Exception exception) {
            log.error("CategoryController: update", exception);
            return new ResponseMessage<>(null, ResultCode.FAIL, exception.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage<Long> delete(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(service.delete(id), ResultCode.SUCCESS, "Категория успешно удалена!", ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CategoryController: delete ", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}