package it.academy.softwarerestoMenu.entity;

import it.academy.softwarerestoMenu.enums.CartStatus;
import it.academy.softwarerestoMenu.enums.Place;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToMany
    private List<Dish> dishes;

    String comment;

    @Enumerated(EnumType.STRING)
    private Place place;

    @Column(nullable = false)
    private BigDecimal total;

    LocalDateTime createDataTime = LocalDateTime.now();
    LocalDateTime removeDateTime;
    LocalDateTime updateDateTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    CartStatus status;


    public BigDecimal calculateTotalAmount() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        if (dishes != null) {
            for (Dish dish : dishes) {
                totalAmount = totalAmount.add(dish.getPrice());
            }
        }
        return totalAmount;
    }

}