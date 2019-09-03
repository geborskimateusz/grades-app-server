package com.helpers.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.ClassroomDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.GradeDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.SubjectDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.TeacherDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Classroom;
import com.mateuszgeborski.gradesbackend.domain.grades.Grade;
import com.mateuszgeborski.gradesbackend.domain.grades.Subject;
import com.mateuszgeborski.gradesbackend.domain.grades.Teacher;

import java.util.Arrays;
import java.util.Collections;

public class SubjectMapperFactory {

    private static final String SUBJECT_NAME = "History";
    private static final long TEACHER_ID = 1L;

    public static Subject getSubject() {
        return Subject.builder()
                .name(SUBJECT_NAME)
                .classrooms(
                        Collections.singletonList(
                                Classroom.testBuilder().build()
                        )
                )
                .grades(
                        Arrays.asList(
                                Grade.builder().build(),
                                Grade.builder().build()
                        )
                )
                .teachers(
                        Collections.singletonList(
                                Teacher.builder().build()
                        )
                )
                .build();
    }

    public static SubjectDTO getSubjectDTO() {
        return SubjectDTO.builder()
                .name(SUBJECT_NAME)
                .classrooms(
                        Collections.singletonList(
                                ClassroomDTO.builder().build()
                        )
                )
                .grades(
                        Arrays.asList(
                                GradeDTO.builder().build(),
                                GradeDTO.builder().build()
                        )
                )
                .teachers(
                        Collections.singletonList(
                                TeacherDTO.builder().id(TEACHER_ID).build()
                        )
                )
                .build();
    }
}
