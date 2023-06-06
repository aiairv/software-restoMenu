package it.academy.softwarerestoMenu.exceptions;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String e) {
        super("Корзина не существует");
    }
}

