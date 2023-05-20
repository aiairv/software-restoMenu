package it.academy.softwarerestoMenu.mapper;

import it.academy.softwarerestoMenu.dto.DishDTO;
import it.academy.softwarerestoMenu.model.Dish;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {
 /*   private final ModelMapper modelMapper;

    @Autowired
    public DishMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static DishDTO convertToDTO(Dish dish) {
        return modelMapper.map(dish, DishDTO.class);
    }

    public Dish convertToEntity(DishDTO dishDTO) {
        return modelMapper.map(dishDTO, Dish.class);
    }

  */
}
