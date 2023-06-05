package it.academy.softwarerestoMenu.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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
