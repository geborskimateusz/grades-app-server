package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.helpers.api.v1.mapper.EntityStringJoiner;
import com.helpers.api.v1.mapper.UserMapperFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.*;
import com.mateuszgeborski.gradesbackend.domain.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    void userDTOtoUser() {
        UserDTO userDTO = UserMapperFactory.getUserDTO();

        User user = userMapper.userDTOtoUser(userDTO);

        assertEquals(
                userDTO.getFirstName() + "," +
                        userDTO.getContact().getEmail() + "," +
                        userDTO.getProfileImage().getImageUrl() + "," +
                        userDTO.getAddress().getCity() + "," +
                        userDTO.getDateOfBirth().toString(),
                EntityStringJoiner.mergeUserToString(user));
    }


    @Test
    void userToUserDTO() {
        User user = UserMapperFactory.getUser();

        UserDTO userDTO = userMapper.userToUserDTO(user);

        assertEquals(
                EntityStringJoiner.mergeUserToString(user),
                userDTO.getFirstName() + "," +
                        userDTO.getContact().getEmail() + "," +
                        userDTO.getProfileImage().getImageUrl() + "," +
                        userDTO.getAddress().getCity() + "," +
                        userDTO.getDateOfBirth().toString());

    }
}