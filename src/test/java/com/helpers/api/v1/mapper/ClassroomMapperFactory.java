package com.helpers.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.ClassroomDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.StudentDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.SubjectDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Classroom;
import com.mateuszgeborski.gradesbackend.domain.grades.Student;
import com.mateuszgeborski.gradesbackend.domain.grades.Subject;

import java.util.Arrays;

public class ClassroomMapperFactory {

    private static final String CLASSROOM = "1B";
    private static final String SUBJECT_HISTORY = "History";
    private static final String SUBJECT_SCIENCE = "Science";

    public static ClassroomDTO getClassroomDTO() {
        return ClassroomDTO.builder()
                .name(CLASSROOM)
                .subjects(
                        Arrays.asList(
                                SubjectDTO.builder().name(SUBJECT_HISTORY).build(),
                                SubjectDTO.builder().name(SUBJECT_SCIENCE).build()
                        )
                )
                .students(
                        Arrays.asList(
                                StudentDTO.builder().id(1L).build(),
                                StudentDTO.builder().id(2L).build()
                        ))
                .build();
    }

    public static Classroom getClassroom() {
        return Classroom.testBuilder()
                .name(CLASSROOM)
                .subjects(
                        Arrays.asList(
                                Subject.builder().name(SUBJECT_HISTORY).build(),
                                Subject.builder().name(SUBJECT_SCIENCE).build()
                        )
                )
                .students(
                        Arrays.asList(
                                Student.builder().id(1L).build(),
                                Student.builder().id(2L).build()
                        ))
                .build();
    }
}
