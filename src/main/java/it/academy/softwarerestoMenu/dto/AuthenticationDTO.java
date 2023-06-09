package it.academy.softwarerestoMenu.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
public class AuthenticationDTO {
    @Email(message = "Не корректный email!")
    private String email;

    @NotEmpty(message = "Пароль не может быть пустым!")
    private String password;
}