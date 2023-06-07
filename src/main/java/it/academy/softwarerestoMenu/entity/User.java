package it.academy.softwarerestoMenu.entity;

import it.academy.softwarerestoMenu.enums.UserStatus;
import it.academy.softwarerestoMenu.enums.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Имя не может быть пустым!")
    @Column(nullable = false)
    private String name;
    @Email(message = "Не корректный email!", regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    @Column(nullable = false)
    private String email;
    @NotEmpty(message = "Пароль не может быть пустым!")
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;
    @Column(name = "activation_token")
    private String activationToken;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "reset_token_expire_time")
    private LocalDateTime resetTokenExpireTime;


    @Column(nullable = false)
    private String phone;
}