package it.academy.softwarerestoMenu.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DishResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isSpecial;
    private Boolean isVegan;
    private Boolean isPublish;
    private CategoryDTO category;
    private List<IngredientDTO> ingredients;

}
