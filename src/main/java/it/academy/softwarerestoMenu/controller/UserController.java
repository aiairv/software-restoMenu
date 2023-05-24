//package it.academy.softwarerestoMenu.controller;
//
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class UserController{
//    @GetMapping("/user")
//    public String getUSerInfo(@AuthenticationPrincipal OAuth2User oauth2User){
//        String name = oauth2User.getAttribute("name");
//        String email = oauth2User.getAttribute("email");
//        return "Welcome, " + name + " (" + email + ")!";
//    }
//}


//package it.academy.softwarerestoMenu.controller;
//
//import ch.qos.logback.core.model.Model;
//import it.academy.softwarerestoMenu.dto.UserDto;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.WebRequest;
//
//@RestController
//@AllArgsConstructor
//
//public class UserController {
//    @GetMapping("/user/registration")
//    public String showRegistrationForm(WebRequest request, Model model) {
//        UserDto userDto = new UserDto();
//        model.addAttribute("user", userDto);
//        return "registration";
//    }
//}
