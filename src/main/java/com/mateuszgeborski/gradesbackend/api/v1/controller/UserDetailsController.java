package com.mateuszgeborski.gradesbackend.api.v1.controller;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.UserDTO;
import com.mateuszgeborski.gradesbackend.api.v1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "${cors.origin.value}")
@RestController
@RequestMapping(UserDetailsController.API_V_1_USER)
public class UserDetailsController {

    public static final String API_V_1_USER = "api/v1/user";
    private final UserService userService;

    public UserDetailsController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{userName}")
    public UserDTO loadUserByUserName(@PathVariable String userName) {
        return userService.loadUserDTOByUsername(userName);
    }
}
