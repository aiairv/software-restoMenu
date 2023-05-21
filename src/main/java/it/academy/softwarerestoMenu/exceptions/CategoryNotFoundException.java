package it.academy.softwarerestoMenu.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
        super("Категория не найдена!");
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
