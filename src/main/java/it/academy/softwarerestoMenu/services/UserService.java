package it.academy.softwarerestoMenu.services;


import it.academy.softwarerestoMenu.entity.User;
import it.academy.softwarerestoMenu.enums.Role;
import it.academy.softwarerestoMenu.enums.UserStatus;
import it.academy.softwarerestoMenu.exceptions.UserNotFoundException;
import it.academy.softwarerestoMenu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public Long deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (user == null)
            return null;

        user.setStatus(UserStatus.DELETED);
        return id;
    }

    public Long updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (existingUser == null)
            return null;

        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setName(user.getName());
        return userRepository.save(existingUser).getId();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<String> activateAccount(String email, String token) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty())
            throw new UserNotFoundException();

        if (!user.get().getActivationToken().equals(token))
            return Collections.singletonList("Неправильный токен активации");

        user.get().setStatus(UserStatus.ACTIVE);
        userRepository.save(user.get());

        return Collections.singletonList("Аккаунт успешно активирован!");
    }

    public void registerUser(User user) throws MessagingException {
        user.setActivationToken(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.PENDING);
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);

        String activationLink = "http://localhost:9090/activate?email=" + user.getEmail() +
                "&token=" + user.getActivationToken();
        emailService.sendActivationEmail(user.getEmail(), activationLink);
    }

    public ResponseEntity<?> resetPassword(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String resetToken = UUID.randomUUID().toString();
        user.get().setResetToken(resetToken);
        user.get().setResetTokenExpireTime(LocalDateTime.now().plusMinutes(30)); // установка срока действия токена
        userRepository.save(user.get());

        String resetUrl = "http://localhost:9090/api/password/reset/" + resetToken;
        String emailText = "Для сброса пароля перейдите по ссылке: " + resetUrl;

        emailService.sendSimpleMessage(email, "Сброс пароля", emailText);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> saveNewPassword(String resetToken, String newPassword) {
        User user = userRepository.findByResetToken(resetToken);
        if (user == null || user.getResetTokenExpireTime().isBefore(LocalDateTime.now()))
            return ResponseEntity.badRequest().build();

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpireTime(null);
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}



//
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public User getUserById(Long id) {
//        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
//
//    }
//
//    public User updateUser(User user) {
//        User existingUser = userRepository.findById(user.getId())
//                .orElseThrow(UserNotFoundException::new);
//        existingUser.setName(user.getName());
//        existingUser.setEmail(user.getEmail());
//        return userRepository.save(existingUser);
//    }
//
//    public void deleteUser(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(UserNotFoundException::new);
//        userRepository.delete(user);
//    }
//
//}
//
