package it.academy.softwarerestoMenu.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private String name;
    private BigDecimal price;
}
