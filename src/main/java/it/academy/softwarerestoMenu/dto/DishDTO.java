package it.academy.softwarerestoMenu.dto;

import it.academy.softwarerestoMenu.model.Category;
import it.academy.softwarerestoMenu.model.Ingredient;
import it.academy.softwarerestoMenu.model.Topping;
import lombok.*;


import java.math.BigDecimal;
import java.util.List;
@Data
@NoArgsConstructor
@Getter
@Setter
public class DishDTO {

    private String name;

    private String description;

    private BigDecimal price;

    private boolean special;
    private boolean vegan;
    private boolean publish;

    private Category category;

    private List<Ingredient> ingredients;

    private List <Topping> toppings;

    public DishDTO(String name, String description, BigDecimal price, boolean special,
                   boolean vegan, boolean publish, Category category,
                   List<Ingredient> ingredients, List<Topping> toppings) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.special = special;
        this.vegan = vegan;
        this.publish = publish;
        this.category = category;
        this.ingredients = ingredients;
        this.toppings = toppings;
    }


}
