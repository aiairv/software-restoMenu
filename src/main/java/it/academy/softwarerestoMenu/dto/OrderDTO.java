package it.academy.softwarerestoMenu.dto;

import it.academy.softwarerestoMenu.entity.User;
import it.academy.softwarerestoMenu.enums.OrderStatus;
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
    private Long id;
    private LocalDateTime orderTime = LocalDateTime.now();
    private OrderStatus orderStatus;
    private List<Long> dishes;
    private List<Long> toppings;
    private User user;

}
