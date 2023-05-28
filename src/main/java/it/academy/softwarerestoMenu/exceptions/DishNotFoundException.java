package it.academy.softwarerestoMenu.exceptions;

public class DishNotFoundException extends RuntimeException {

    public DishNotFoundException() {
        super("Dish not found!");
    }

    public DishNotFoundException(String message) {
        super(message);
    }
}
