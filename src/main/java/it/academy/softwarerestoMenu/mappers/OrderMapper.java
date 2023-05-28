package it.academy.softwarerestoMenu.mappers;

import it.academy.softwarerestoMenu.dto.OrderDTO;
import it.academy.softwarerestoMenu.entity.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderDTO toDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    public Order toEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }
}
