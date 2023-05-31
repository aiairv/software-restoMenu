package it.academy.softwarerestoMenu.dto;

import it.academy.softwarerestoMenu.entity.Ingredient;
import it.academy.softwarerestoMenu.entity.Topping;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    private Long categoryId;
    private List<Long> ingredientIds;
    private List<Long> toppingIds;

}
