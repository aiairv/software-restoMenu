package it.academy.softwarerestoMenu.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DishDTO {
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private boolean isSpecial;

    private boolean isVegan;

    private boolean isPublish;

}
