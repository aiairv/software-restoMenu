package it.academy.softwarerestoMenu.entity;

import it.academy.softwarerestoMenu.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusRequest {
    private OrderStatus status;
}
