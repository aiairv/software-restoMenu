package it.academy.softwarerestoMenu.entity;

import it.academy.softwarerestoMenu.enums.Place;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Place place;
    private LocalDateTime reservationTime;
    private LocalDateTime reservedTime;
    private boolean isAvailable;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
