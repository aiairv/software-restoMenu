package it.academy.softwarerestoMenu.dto;
import it.academy.softwarerestoMenu.entity.Dish;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class CategoryDTO {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private boolean isPublish;
        private Dish dish;



}
