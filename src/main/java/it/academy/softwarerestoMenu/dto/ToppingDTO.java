package it.academy.softwarerestoMenu.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ToppingDTO {
    private String name;
    private BigDecimal price;
}
