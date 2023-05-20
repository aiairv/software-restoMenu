package it.academy.softwarerestoMenu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dish")
    public class Dish {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false)
        private String name;
        @Column(nullable = false)
        private String Description;
        @Column(nullable = false)
        private BigDecimal price;
        @Column(nullable = false)
        private boolean isSpecial;
        @Column(nullable = false)
        private boolean isVegan;
        @Column(nullable = false)
        private boolean isPublish;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id")
        private Category category;
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "dish_ingredients", joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List <Ingredient> ingredients;
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "topping", joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "topping_id"))
    private List <Topping> toppings;
}
