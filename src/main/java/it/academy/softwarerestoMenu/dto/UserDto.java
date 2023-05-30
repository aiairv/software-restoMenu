package it.academy.softwarerestoMenu.dto;

import it.academy.softwarerestoMenu.enums.Role;
import lombok.Data;

//import org.jetbrains.annotations.NotNull;
//
@Data
public class UserDto {
    private String email;
    private String phoneNumber;
    private String password;
    private Role role;

}
//        @NotNull
//        private String firstName;
//
//        @NotNull
//        private String lastName;
//
//        @NotNull
//        private String password;
//        private String matchingPassword;
//
//        @NotNull
//        private String email;
//
//        public UserDto() {
//        }
//
//        // standard getters and setters
//    }

