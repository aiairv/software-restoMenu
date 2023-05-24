//package it.academy.softwarerestoMenu.service;
//
//import it.academy.softwarerestoMenu.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.apache.catalina.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//@NoArgsConstructor
//public class MyUserDetailsService implements UserDetailsService {
//
//    UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//        User user = userRepository.getUserByHisUsername(username);
//        return org.springframework.security.core.userdetails.User
//                .builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .roles(new String[0])
//                .build();
//    }
//}
