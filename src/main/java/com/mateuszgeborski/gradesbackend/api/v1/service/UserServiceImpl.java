package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.mapper.UserMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.UserDTO;
import com.mateuszgeborski.gradesbackend.api.v1.repository.UserRepository;
import com.mateuszgeborski.gradesbackend.domain.user.Role;
import com.mateuszgeborski.gradesbackend.domain.user.User;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper = UserMapper.INSTANCE;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.userDTOtoUser(userDTO);
        User saved = userRepository.save(user);
        return userMapper.userToUserDTO(saved);
    }

    @Override
    public UserDTO findById(Long userId) {
        return userMapper.userToUserDTO(
                userRepository.findById(userId)
                        .orElseThrow(ResourceNotFoundException::new)
        );
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userToUserDetails(loadByUsername(s));
    }

    @Override
    public User loadByUsername(String username) throws UsernameNotFoundException {
        Optional<User> findFirst = userRepository.findAll().stream()
                .filter(user -> user.getUsername().equals(username)).findFirst();

        if (!findFirst.isPresent()) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }

        return findFirst.get();
    }

    @Override
    public UserDTO loadUserDTOByUsername(String username) {
        return userMapper.userToUserDTO(loadByUsername(username));
    }

    public UserDetails userToUserDetails(User user) {
        return new
                org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new
                SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
