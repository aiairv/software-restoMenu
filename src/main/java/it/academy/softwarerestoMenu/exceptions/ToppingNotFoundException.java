package it.academy.softwarerestoMenu.exceptions;

public class ToppingNotFoundException extends RuntimeException {

    public ToppingNotFoundException() {
        super("Topping not found!");
    }

    public ToppingNotFoundException(String message) {
        super(message);
    }
}
