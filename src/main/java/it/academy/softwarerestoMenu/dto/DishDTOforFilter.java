package it.academy.softwarerestoMenu.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DishDTOforFilter {
    private String name;
    private String description;
    private BigDecimal price;

}
