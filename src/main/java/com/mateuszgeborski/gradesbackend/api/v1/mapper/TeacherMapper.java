package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.TeacherDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    TeacherDTO teacherToTeacherDTO(Teacher teacher);

    Teacher teacherDTOtoTeacher(TeacherDTO teacherDTO);
}
