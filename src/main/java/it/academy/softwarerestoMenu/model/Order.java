package it.academy.softwarerestoMenu.model;

import it.academy.softwarerestoMenu.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderTime;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany
    @JoinColumn(name = "dish_id")
    private List<Dish> dishes;
    @OneToMany
    @JoinColumn(name = "topping_id")
    private List<Topping> toppings;
    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;
}
