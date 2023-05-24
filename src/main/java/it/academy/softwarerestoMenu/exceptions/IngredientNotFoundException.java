package it.academy.softwarerestoMenu.exceptions;

public class IngredientNotFoundException extends RuntimeException {

    public IngredientNotFoundException() {
        super("Ингредиент не найден!");
    }

    public IngredientNotFoundException(String message) {
        super(message);
    }
}
