package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.ClassroomDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Qualifier;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {

    ClassroomMapper INSTANCE = Mappers.getMapper(ClassroomMapper.class);

    Classroom classroomDTOtoClassroom(ClassroomDTO classroomDTO);

    ClassroomDTO classroomToClassroomDTO(Classroom classroom);

    @Named(value = "classroomToClassroomDTOwithoutCollections")
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    ClassroomDTO classroomToClassroomDTOwithoutCollections(Classroom classroom);

}
