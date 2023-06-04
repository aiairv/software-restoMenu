package it.academy.softwarerestoMenu.entity;

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

    private LocalDateTime orderTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;

    @OneToOne
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
