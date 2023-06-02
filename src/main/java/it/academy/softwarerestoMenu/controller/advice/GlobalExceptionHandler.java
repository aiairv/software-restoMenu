package it.academy.softwarerestoMenu.controller.advice;

import it.academy.softwarerestoMenu.exceptions.UserErrorResponse;
import it.academy.softwarerestoMenu.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final UserErrorResponse userErrorResponse;


    @Autowired
    public GlobalExceptionHandler(UserErrorResponse userErrorResponse) {
        this.userErrorResponse = userErrorResponse;
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        userErrorResponse.setMessage("Пользователь с таким id не найден!");
        userErrorResponse.setTimestamp(LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        return new ResponseEntity<>(userErrorResponse, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}
