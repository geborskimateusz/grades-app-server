package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.RoleDTO;
import com.mateuszgeborski.gradesbackend.domain.user.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role roleDTOtoRole(RoleDTO roleDTO);

    RoleDTO roleToRoleDTO(Role role);
}
