package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.helpers.api.v1.mapper.EntityStringJoiner;
import com.helpers.api.v1.mapper.SubjectMapperFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.SubjectDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Subject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubjectMapperTest {

    SubjectMapper subjectMapper = SubjectMapper.INSTANCE;

    @Test
    void subjectToSubjectDTO() {
        Subject expected = SubjectMapperFactory.getSubject();

        SubjectDTO actual = subjectMapper.subjectToSubjectDTO(expected);

        assertEquals(
                EntityStringJoiner.mergeSubjectToString(expected),
                actual.getName() + "," +
                        actual.getClassrooms().size() + "," +
                        actual.getGrades().get(0).getLetter() + "," +
                        actual.getTeachers().get(0).getId()
        );
    }

    @Test
    void subjectDTOtoSubject() {
        SubjectDTO expected = SubjectMapperFactory.getSubjectDTO();

        Subject actual = subjectMapper.subjectDTOtoSubject(expected);

        assertEquals(expected.getName() + "," +
                        expected.getClassrooms().size() + "," +
                        expected.getGrades().get(0).getLetter() + "," +
                        expected.getTeachers().get(0).getId(),
                EntityStringJoiner.mergeSubjectToString(actual));
    }
}