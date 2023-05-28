package it.academy.softwarerestoMenu.mappers;

import it.academy.softwarerestoMenu.dto.DishDTO;
import it.academy.softwarerestoMenu.entity.Dish;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DishMapper {
    private final ModelMapper modelMapper;

    public Dish map(DishDTO dishDTO) {
        return modelMapper.map(dishDTO, Dish.class);
    }

    public DishDTO map(Dish dish) {
        return modelMapper.map(dish, DishDTO.class);
    }


}