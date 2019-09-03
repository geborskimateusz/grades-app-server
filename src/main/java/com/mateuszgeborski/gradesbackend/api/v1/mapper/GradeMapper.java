package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.GradeDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    GradeMapper INSTANCE = Mappers.getMapper(GradeMapper.class);

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "subject", ignore = true)
    GradeDTO gradeToGradeDTO(Grade grade);

    Grade gradeDTOtoGrade(GradeDTO gradeDTO);
}
