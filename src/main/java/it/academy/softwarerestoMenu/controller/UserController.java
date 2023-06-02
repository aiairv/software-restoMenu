package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.UserDTOForReg;
import it.academy.softwarerestoMenu.entity.User;
import it.academy.softwarerestoMenu.mappers.UserMapper;
import it.academy.softwarerestoMenu.security.UserDetails;
import it.academy.softwarerestoMenu.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/current")
    public UserDTOForReg getUserById(@AuthenticationPrincipal UserDetails userDetails) {
        return userMapper.convertToDTO(
                userService.getUserById(userDetails.getUser().getId()));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Long updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid UserDTOForReg userDTOForReg) {
        return userService.updateUser(userDetails.getUser().getId(),
                userMapper.convertToEntity(userDTOForReg));
    }

//    @PostMapping("/")
//    @ResponseStatus(HttpStatus.CREATED)
//    public User createUser(@RequestBody User user) {
//        return userService.createUser(user);
//    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}

//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable Long id, @RequestBody User user) {
//        if (!id.equals(user.getId())) {
//            throw new IllegalArgumentException("Несоответствие ID");
//        }
//        return userService.updateUser(user);
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//    }
//}
