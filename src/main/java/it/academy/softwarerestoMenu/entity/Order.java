package it.academy.softwarerestoMenu.entity;

import it.academy.softwarerestoMenu.entity.Cart;
import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.entity.Topping;
import it.academy.softwarerestoMenu.entity.User;
import it.academy.softwarerestoMenu.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

//package it.academy.softwarerestoMenu.entity;
//
//import it.academy.softwarerestoMenu.enums.OrderStatus;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//    @Entity
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Table(name = "orders")
//    @Getter
//    @Setter
//    public class Order {
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        private LocalDateTime orderTime=LocalDateTime.now();
//        @Enumerated(EnumType.STRING)
//        private OrderStatus orderStatus;
//        @OneToMany
//        @JoinColumn(name = "dish_id")
//        private List<Dish> dishes;
//        @OneToMany
//        @JoinColumn(name = "topping_id")
//        private List<Topping> toppings;
////        @OneToMany(mappedBy = "order", cascade = CascadeType.   ALL)
////        private List<OrderItem> orderItems;
//        @ManyToOne
//        @JoinColumn(name = "user_id")
//        private User user;
//        @OneToOne
//        private Cart cart;
//    }
    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "orders")
    @Getter
    @Setter
    public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

//    @ManyToMany
//    @JoinTable(
//            name = "order_toppings",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "topping_id")
//    )
//    private List<Topping> toppings;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private Cart cart;
    private BigDecimal totalAmount;
}