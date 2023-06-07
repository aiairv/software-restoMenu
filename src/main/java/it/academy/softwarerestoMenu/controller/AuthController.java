package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.AuthenticationDTO;
import it.academy.softwarerestoMenu.dto.UserDTOForReg;
import it.academy.softwarerestoMenu.entity.User;
import it.academy.softwarerestoMenu.enums.UserStatus;
import it.academy.softwarerestoMenu.mappers.UserMapper;
import it.academy.softwarerestoMenu.security.JWTUtil;
import it.academy.softwarerestoMenu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import it.academy.softwarerestoMenu.util.UserValidator;


import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserValidator userValidator;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @Autowired
    public AuthController(UserService userService, UserMapper userMapper, UserValidator userValidator, AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userValidator = userValidator;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/activate")
    public List<String> activateAccount(@RequestParam("email") String email, @RequestParam("token") String token) {
        return userService.activateAccount(email, token);
    }

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody @Valid UserDTOForReg userDTOForReg,
                                            BindingResult bindingResult) throws MessagingException {
        userValidator.validate(userMapper.convertToEntity(userDTOForReg), bindingResult);
        if (bindingResult.hasErrors())
            return Map.of("message", bindingResult.getFieldErrors().toString());

        userService.registerUser(userMapper.convertToEntity(userDTOForReg));

        String token = jwtUtil.generateToken(userDTOForReg.getEmail());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        Optional<User> user = userService.findByEmail(authenticationDTO.getUsername());

        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                        authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        if (user.isEmpty() || user.get().getStatus() != UserStatus.ACTIVE)
            return Map.of("message", "User is not active!");

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("jwt-token", token);
    }

}
