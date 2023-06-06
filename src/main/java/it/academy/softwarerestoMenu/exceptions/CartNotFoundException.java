package it.academy.softwarerestoMenu.exceptions;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException() {
        super("Корзина не существует");
    }

    public CartNotFoundException(String e) {
        super(e);
    }
}

