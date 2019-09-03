package com.helpers.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.ClassroomDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.GradeDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.StudentDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Classroom;
import com.mateuszgeborski.gradesbackend.domain.grades.Grade;
import com.mateuszgeborski.gradesbackend.domain.grades.Student;

import java.util.Arrays;

public class StudentMapperFactory {

    private static final long STUDENT_ID = 1L;
    private static final String CLASSROOM_NAME = "1B";
    private static final String A_LETTER = "A";
    private static final String B_LETTER = "B";

    public static Student getStudent() {
        return Student.builder()
                .id(STUDENT_ID)
                .classroom(
                        Classroom.testBuilder()
                                .name(CLASSROOM_NAME)
                                .build()
                )
                .grades(
                        Arrays.asList(
                                Grade.builder()
                                        .letter(A_LETTER)
                                        .build(),
                                Grade.builder()
                                        .letter(B_LETTER)
                                        .build()
                        )
                )
                .build();
    }

    public static StudentDTO getStudentDTO() {
        return StudentDTO.builder()
                .id(STUDENT_ID)
                .classroom(
                        ClassroomDTO.builder()
                                .name(CLASSROOM_NAME)
                                .build()
                )
                .grades(
                        Arrays.asList(
                                GradeDTO.builder()
                                        .letter(A_LETTER)
                                        .build(),
                                GradeDTO.builder()
                                        .letter(B_LETTER)
                                        .build()
                        )
                )
                .build();
    }
}
