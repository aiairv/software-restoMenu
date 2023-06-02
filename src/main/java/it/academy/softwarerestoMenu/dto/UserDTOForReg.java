package it.academy.softwarerestoMenu.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
public class UserDTOForReg {
    @NotEmpty(message = "Имя не может быть пустым!")
//    private String fullName;
    private String name;

    @Email(message = "Не корректный email!", regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private String email;

    @NotEmpty(message = "Пароль не может быть пустым!")
    private String password;
    @NotEmpty(message = "Номер телефона не может быть пустым!")
    private String phone;
}
