package it.academy.softwarerestoMenu.entity;

import it.academy.softwarerestoMenu.enums.Place;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private boolean isAvailable;

//    @OneToOne
//    @JoinColumn(name = "order_id")
//    private Order order;

}
