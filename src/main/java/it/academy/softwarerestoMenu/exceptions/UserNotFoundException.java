package it.academy.softwarerestoMenu.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("Пользователь не найден");
    }
}
