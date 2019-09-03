package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.helpers.api.v1.mapper.EntityStringJoiner;
import com.helpers.api.v1.mapper.GradeMapperFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.GradeDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Grade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeMapperTest {

    GradeMapper gradeMapper = GradeMapper.INSTANCE;

    @Test
    void gradeToGradeDTO() {

        Grade expected = GradeMapperFactory.getGrade();

        GradeDTO actual = gradeMapper.gradeToGradeDTO(expected);

        assertEquals(
                EntityStringJoiner.mergeGradeToString(expected),
                actual.getLetter());
    }

    @Test
    void gradeDTOtoGrade() {

        GradeDTO expected = GradeMapperFactory.getGradeDTO();

        Grade actual = gradeMapper.gradeDTOtoGrade(expected);

        assertEquals(
                expected.getLetter(),
                EntityStringJoiner.mergeGradeToString(actual)
        );
    }
}