package it.academy.softwarerestoMenu.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {
        super("Заказ не найден");
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
