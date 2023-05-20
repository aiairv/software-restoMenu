package it.academy.softwarerestoMenu.exceptions;

public class ConsumerNotFoundException extends RuntimeException{

    public ConsumerNotFoundException() {
        super("Consumer not found");
    }
}
