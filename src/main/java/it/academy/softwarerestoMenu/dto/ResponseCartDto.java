package it.academy.softwarerestoMenu.dto;

import it.academy.softwarerestoMenu.enums.Place;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCartDto {
    List<DishDtoForFilter> dishDtoForFilters;

    private BigDecimal total;

    String comment;
    Place place;

}
