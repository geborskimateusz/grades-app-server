package com.helpers.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.SubjectDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.TeacherDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Subject;
import com.mateuszgeborski.gradesbackend.domain.grades.Teacher;

import java.util.Arrays;

public class TeacherMapperFactory {

    private static final long TEACHER_ID = 1L;
    private static final String HISTORY_SUBJECT = "History";
    private static final String SCIENCE_SUBJECT = "Science";

    public static Teacher getTeacher() {
        return Teacher.builder()
                .id(TEACHER_ID)
                .subjects(
                        Arrays.asList(
                                Subject.builder()
                                        .name(HISTORY_SUBJECT)
                                        .build(),
                                Subject.builder()
                                        .name(SCIENCE_SUBJECT)
                                        .build()
                        )
                )
                .build();
    }

    public static TeacherDTO getTeacherDTO() {
        return TeacherDTO.builder()
                .id(TEACHER_ID)
                .subjects(
                        Arrays.asList(
                                SubjectDTO.builder()
                                        .name(HISTORY_SUBJECT)
                                        .build(),
                                SubjectDTO.builder()
                                        .name(HISTORY_SUBJECT)
                                        .build()
                        )
                )
                .build();
    }
}
