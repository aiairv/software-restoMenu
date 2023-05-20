package it.academy.softwarerestoMenu.dto;

import it.academy.softwarerestoMenu.model.Category;
import it.academy.softwarerestoMenu.model.Ingredient;
import it.academy.softwarerestoMenu.model.Topping;
import lombok.Data;


import java.math.BigDecimal;
import java.util.List;
@Data
public class DishDTO {

    private String name;

    private String Description;

    private BigDecimal price;

    private boolean isSpecial;

    private boolean isVegan;

    private boolean isPublish;

    private Category category;

    private List<Ingredient> ingredients;

    private List <Topping> toppings;
}
