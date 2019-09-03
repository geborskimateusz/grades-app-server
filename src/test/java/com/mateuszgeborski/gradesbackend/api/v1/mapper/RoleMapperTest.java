package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.helpers.api.v1.mapper.RoleMapperFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.RoleDTO;
import com.mateuszgeborski.gradesbackend.domain.user.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleMapperTest {

    RoleMapper roleMapper = RoleMapper.INSTANCE;

    @Test
    void roleDTOtoRole() {
        Role role = RoleMapperFactory.getRole();

        RoleDTO roleDTO = roleMapper.roleToRoleDTO(role);

        assertEquals(role.getName(), roleDTO.getName());
    }

    @Test
    void roleToRoleDTO() {
        RoleDTO roleDTO = RoleMapperFactory.getRoleDTO();

        Role role = roleMapper.roleDTOtoRole(roleDTO);

        assertEquals(roleDTO.getName(), role.getName());
    }
}