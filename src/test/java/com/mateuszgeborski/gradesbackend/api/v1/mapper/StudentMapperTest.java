package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.helpers.api.v1.mapper.EntityStringJoiner;
import com.helpers.api.v1.mapper.StudentMapperFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.StudentDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    StudentMapper studentMapper = StudentMapper.INSTANCE;

    @Test
    void studentDTOtoStudent() {
        StudentDTO expected = StudentMapperFactory.getStudentDTO();

        Student actual = studentMapper.studentDTOtoStudent(expected);


        assertEquals(
                String.valueOf(expected.getId()) + "," +
                        expected.getClassroom().getName() + "," +
                        expected.getGrades().get(0).getLetter(),
                EntityStringJoiner.mergeStudentToString(actual)
        );
    }

    @Test
    void studentToStudentDTO() {
        Student expected = StudentMapperFactory.getStudent();

        StudentDTO actual = studentMapper.studentToStudentDTO(expected);

        assertEquals(
                EntityStringJoiner.mergeStudentToString(expected),
                String.valueOf(actual.getId()) + "," +
                        actual.getClassroom().getName() + "," +
                        actual.getGrades().get(0).getLetter()
        );
    }
}