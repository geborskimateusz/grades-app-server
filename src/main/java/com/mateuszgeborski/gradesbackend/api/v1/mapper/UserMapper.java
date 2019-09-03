package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.UserDTO;
import com.mateuszgeborski.gradesbackend.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "address.user", ignore = true)
    @Mapping(target = "contact.user", ignore = true)
    @Mapping(target = "profileImage.user", ignore = true)
    UserDTO userToUserDTO(User user);

    User userDTOtoUser(UserDTO userDTO);
}
