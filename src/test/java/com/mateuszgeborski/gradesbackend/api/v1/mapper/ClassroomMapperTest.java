package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.helpers.api.v1.mapper.ClassroomMapperFactory;
import com.helpers.api.v1.mapper.EntityStringJoiner;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.ClassroomDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Classroom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassroomMapperTest {

    ClassroomMapper classroomMapper = ClassroomMapper.INSTANCE;

    @Test
    void classroomDTOtoClassroom() {
        Classroom expected = ClassroomMapperFactory.getClassroom();

        ClassroomDTO actual = classroomMapper.classroomToClassroomDTO(expected);

        assertEquals(
                EntityStringJoiner.mergeClassroomDataToString(expected),
                actual.getName() + "," +
                        String.valueOf(actual.getSubjects().size()) + "," +
                        actual.getSubjects().get(0).getName() + "," +
                        String.valueOf(actual.getStudents().get(0).getId())
        );
    }


    @Test
    void classroomToClassroomDTO() {
        ClassroomDTO expected = ClassroomMapperFactory.getClassroomDTO();

        Classroom actual = classroomMapper.classroomDTOtoClassroom(expected);

        assertEquals(
                expected.getName() + "," +
                        String.valueOf(expected.getSubjects().size()) + "," +
                        expected.getSubjects().get(0).getName() + "," +
                        String.valueOf(expected.getStudents().get(0).getId()),
                EntityStringJoiner.mergeClassroomDataToString(actual)

        );
    }
}