package it.academy.softwarerestoMenu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.academy.softwarerestoMenu.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated
    private Role role;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Cart> carts;
}

