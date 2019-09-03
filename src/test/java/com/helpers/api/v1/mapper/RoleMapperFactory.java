package com.helpers.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.RoleDTO;
import com.mateuszgeborski.gradesbackend.domain.user.Role;

public class RoleMapperFactory {

    private static final String STUDENT_ROLE = "STUDENT";

    public static Role getRole() {
        return Role.builder().name(STUDENT_ROLE).build();
    }

    public static RoleDTO getRoleDTO() {
        return RoleDTO.builder().name(STUDENT_ROLE).build();
    }
}
