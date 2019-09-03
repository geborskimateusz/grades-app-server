package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.StudentDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student studentDTOtoStudent(StudentDTO studentDTO);

    StudentDTO studentToStudentDTO(Student student);


}
