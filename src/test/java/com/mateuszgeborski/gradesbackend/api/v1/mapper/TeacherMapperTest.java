package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.helpers.api.v1.mapper.EntityStringJoiner;
import com.helpers.api.v1.mapper.TeacherMapperFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.TeacherDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Teacher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherMapperTest {

    TeacherMapper teacherMapper = TeacherMapper.INSTANCE;

    @Test
    void teacherDTOtoTeacher() {
        TeacherDTO expected = TeacherMapperFactory.getTeacherDTO();

        Teacher actual = teacherMapper.teacherDTOtoTeacher(expected);

        assertEquals(
                String.valueOf(expected.getId()) + "," +
                        expected.getSubjects().get(0).getName(),
                EntityStringJoiner.mergeTeacherToString(actual)
        );
    }

    @Test
    void teacherToTeacherDTO() {
        Teacher expected = TeacherMapperFactory.getTeacher();

        TeacherDTO actual = teacherMapper.teacherToTeacherDTO(expected);

        assertEquals(
                EntityStringJoiner.mergeTeacherToString(expected),
                String.valueOf(actual.getId()) + "," +
                        actual.getSubjects().get(0).getName());
    }
}