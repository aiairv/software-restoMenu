package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.UserDTOForAdmin;
import it.academy.softwarerestoMenu.mappers.UserDTOForAdminMapper;
import it.academy.softwarerestoMenu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final UserDTOForAdminMapper userMapper;

    @Autowired
    public AdminController(UserService userService, UserDTOForAdminMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/user/all")
    public List<UserDTOForAdmin> getAllUsers() {
        return userService.getAllUsers().stream().map(
                userMapper::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public UserDTOForAdmin getUserById(@PathVariable Long id) {
        return userMapper.convertToDTO(userService.getUserById(id));
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }
}

