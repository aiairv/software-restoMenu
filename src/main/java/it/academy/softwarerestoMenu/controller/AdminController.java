package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.UserDTOForAdmin;
import it.academy.softwarerestoMenu.entity.Category;
import it.academy.softwarerestoMenu.entity.ResponseMessage;
import it.academy.softwarerestoMenu.enums.ResultCode;
import it.academy.softwarerestoMenu.mappers.UserDTOForAdminMapper;
import it.academy.softwarerestoMenu.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@Slf4j
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
    public ResponseMessage <Long> deleteUserById(@PathVariable Long id) {
        try { return new ResponseMessage<>(
                userService.deleteUserById(id), ResultCode.SUCCESS, "Пользователь по ID найден", ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: delete ", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

}

