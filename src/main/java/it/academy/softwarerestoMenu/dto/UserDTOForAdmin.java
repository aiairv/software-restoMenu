package it.academy.softwarerestoMenu.dto;

import it.academy.softwarerestoMenu.enums.UserStatus;
import it.academy.softwarerestoMenu.enums.Role;
import lombok.Data;

@Data
public class UserDTOForAdmin {
    private String name;
    private String email;
    private String password;
    private Role role;
    private UserStatus status;
}
