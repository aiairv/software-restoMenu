//package it.academy.softwarerestoMenu.service;
//
//import it.academy.softwarerestoMenu.entity.User;
//import it.academy.softwarerestoMenu.enums.Role;
//import it.academy.softwarerestoMenu.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class UserService {
//    UserRepository userRepository;
//    public User registerUser(String username, String password, Role role){
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(passwordEncoder().encode(password));
//        user.setRole(role);
//        return userRepository.save(user);
//    }
//
//    public UserDetails getUserByUsername(String username){
//        User user = (User) userRepository.findByUsername(username);
//        if (user == null) {
//            throw new  UsernameNotFoundException("Invalid username");
//        }
//        return new user;
//    }
//}