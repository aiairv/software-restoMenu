package it.academy.softwarerestoMenu.dto;

import it.academy.softwarerestoMenu.entity.Dish;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private Boolean isPublish;
}
