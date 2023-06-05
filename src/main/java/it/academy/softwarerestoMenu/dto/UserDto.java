package it.academy.softwarerestoMenu.dto;

import it.academy.softwarerestoMenu.entity.Order;
import lombok.Data;

import java.util.List;
@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<Order> orders;
}
