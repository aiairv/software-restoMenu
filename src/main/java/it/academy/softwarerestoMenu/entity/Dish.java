package it.academy.softwarerestoMenu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "dish")
@Getter
@Setter
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Boolean isSpecial;
    @Column(nullable = false)
    private Boolean isVegan;
    @Column(nullable = false)
    private Boolean isPublish;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dish_ingredients", joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dish_toppings", joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "topping_id"))
    private List<Topping> toppings;
    LocalDateTime createDataTime = LocalDateTime.now();
    LocalDateTime removeDateTime;
    LocalDateTime updateDateTime = LocalDateTime.now();


}
