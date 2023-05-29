package it.academy.softwarerestoMenu.exceptions;

public class DishNotFoundException extends RuntimeException {

    public DishNotFoundException() {
        super("Блюдо не найдено!");
    }

    public DishNotFoundException(String message) {
        super(message);
    }
}