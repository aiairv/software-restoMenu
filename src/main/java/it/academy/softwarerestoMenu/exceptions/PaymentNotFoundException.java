package it.academy.softwarerestoMenu.exceptions;

public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException() {
        super("Платеж не найден!");
    }

    public PaymentNotFoundException(String message) {
        super(message);
    }
}
