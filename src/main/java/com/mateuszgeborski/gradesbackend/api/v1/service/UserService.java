package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.UserDTO;
import com.mateuszgeborski.gradesbackend.domain.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService{
    UserDTO save(UserDTO userDTO);
    UserDTO findById(Long userId);
    UserDTO loadUserDTOByUsername(String username);
    User loadByUsername(String username);
}
