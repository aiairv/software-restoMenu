//package it.academy.softwarerestoMenu.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "username", nullable = false, unique = true)
//    private String username;
//    @Column(name = "email", nullable = false, unique = true)
//    private String email;
//    @Column(name = "phone_number", nullable = false, unique = true)
//    private String phoneNumber;
//    @Column(name = "password", nullable = false)
//    private String password;
//    @OneToOne
//    private Cart cart;

////    @Enumerated(value = EnumType.STRING)
////    private AuthStatus authStatus;
//    @Enumerated(value = EnumType.STRING)
//    private Role role;
//
////    @OneToMany
////    private List<Order> orders;
////    @OneToOne
////    private Cart cart;

//}