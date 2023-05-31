package it.academy.softwarerestoMenu.dto;

import it.academy.softwarerestoMenu.entity.Dish;
import it.academy.softwarerestoMenu.entity.Topping;
import it.academy.softwarerestoMenu.entity.User;
import it.academy.softwarerestoMenu.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {
    private LocalDateTime orderTime=LocalDateTime.now();
    private OrderStatus orderStatus;
    private List<Dish> dishesIds;

    private List<Topping> toppingsIds;
    private User userId;
}
