package it.academy.softwarerestoMenu.dto;

import java.math.BigDecimal;
import java.util.List;

public class DishDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean isSpecial;
    private boolean isVegan;
    private boolean isPublish;
    private List<Long> ingredientIds;
    private List<Long> toppingIds;

}
