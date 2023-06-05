package it.academy.softwarerestoMenu.entity;

import it.academy.softwarerestoMenu.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;

    @OneToOne
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    private Cart cart;
}
