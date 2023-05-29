package it.academy.softwarerestoMenu.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrderDto {
    private Long id;
    private String orderStatus;
    private LocalDateTime orderTime;
}
