package it.academy.softwarerestoMenu.mappers;

import it.academy.softwarerestoMenu.dto.UserDTOForAdmin;
import it.academy.softwarerestoMenu.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDTOForAdminMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserDTOForAdminMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTOForAdmin convertToDTO(User user) {
        return modelMapper.map(user, UserDTOForAdmin.class);
    }

    public User convertToEntity(UserDTOForAdmin userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}