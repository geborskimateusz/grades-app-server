package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.SubjectDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    SubjectDTO subjectToSubjectDTO(Subject subject);

    Subject subjectDTOtoSubject(SubjectDTO subjectDTO);
}

