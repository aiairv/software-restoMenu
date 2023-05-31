package it.academy.softwarerestoMenu.controller;
import it.academy.softwarerestoMenu.entity.User;

import it.academy.softwarerestoMenu.enums.Role;
import it.academy.softwarerestoMenu.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class RegistrationController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam("username") String username,
                                             @RequestParam("password") String password) {
        User registeredUser = userService.registerUser(username, password, Role.CLIENT);
        return ResponseEntity.ok(registeredUser);
    }
}