package it.academy.softwarerestoMenu.entity;

import it.academy.softwarerestoMenu.enums.OrderStatus;
import it.academy.softwarerestoMenu.enums.PaymentEnum;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

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

    @OneToOne
    private Payment payment;

    @Enumerated(EnumType.STRING)
    PaymentEnum paymentEnum;

    @OneToOne
    private Cart cart;
}