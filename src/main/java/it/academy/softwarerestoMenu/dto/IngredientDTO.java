package it.academy.softwarerestoMenu.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class IngredientDTO {
    private Long id;
    private String name;
}
