package it.academy.softwarerestoMenu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isPublish;
    LocalDateTime createDataTime = LocalDateTime.now();
    LocalDateTime removeDateTime;
    LocalDateTime updateDateTime = LocalDateTime.now();
    public Category(String name, boolean isPublish) {
        this.name = name;
        this.isPublish = isPublish;
    }
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List <Dish> dishes;
    }

