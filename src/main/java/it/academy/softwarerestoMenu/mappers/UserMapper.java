package it.academy.softwarerestoMenu.mappers;

import it.academy.softwarerestoMenu.dto.UserDTOForReg;
import it.academy.softwarerestoMenu.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTOForReg convertToDTO(User user) {
        return modelMapper.map(user, UserDTOForReg.class);
    }

    public User convertToEntity(UserDTOForReg userDTOForReg) {
        return modelMapper.map(userDTOForReg, User.class);
    }

}
