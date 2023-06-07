package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.repository.UserRepository;
import it.academy.softwarerestoMenu.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import it.academy.softwarerestoMenu.entity.User;

import java.util.Optional;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found!");

        return new UserDetails(user.get());
    }
}

