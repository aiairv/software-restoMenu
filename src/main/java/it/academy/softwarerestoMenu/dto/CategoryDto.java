package it.academy.softwarerestoMenu.dto;

import it.academy.softwarerestoMenu.entity.Dish;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
@Data
public class CategoryDto {
    private Long id;
    private String name;
    private boolean isPublish;
}
