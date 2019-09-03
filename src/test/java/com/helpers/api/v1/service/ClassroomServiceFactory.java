package com.helpers.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.ClassroomDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.StudentDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.SubjectDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Classroom;
import com.mateuszgeborski.gradesbackend.domain.grades.Student;
import com.mateuszgeborski.gradesbackend.domain.grades.Subject;
import com.mateuszgeborski.gradesbackend.domain.grades.Teacher;

import java.util.Arrays;
import java.util.Collections;

public class ClassroomServiceFactory {

    public static final long CLASSROOM_ID = 2L;
    public static final long TEACHER_ID = 1L;
    public static final String SUBJECT_NAME = "History";
    public static final String CLASSROM_NAME = "1B";

    public static Classroom getClassroom() {
        return Classroom.testBuilder()
                .id(CLASSROOM_ID)
                .name(CLASSROM_NAME)
                .subjects(
                        Collections.singletonList(
                                getSubjectOfClassroom()
                        ))
                .students(
                        Collections.singletonList(
                                Student.builder().build()
                        )
                ).build();
    }

    public static ClassroomDTO getClassroomDTO() {
        return ClassroomDTO.builder()
                .name(CLASSROM_NAME)
                .subjects(
                        Collections.singletonList(
                                SubjectDTO.builder().build()
                        ))
                .students(
                        Collections.singletonList(
                                StudentDTO.builder().build()
                        )
                ).build();
    }

    private static Subject getSubjectOfClassroom() {
        return Subject.builder()
                .name(SUBJECT_NAME)
                .classrooms(
                        Collections.singletonList(
                                Classroom.testBuilder().id(CLASSROOM_ID).build()
                        ))
                .teachers(
                        Collections.singletonList(
                                Teacher.builder()
                                        .id(TEACHER_ID)
                                        .subjects(
                                                Collections.singletonList(
                                                        Subject.builder()
                                                                .name(SUBJECT_NAME)
                                                                .classrooms(
                                                                        Collections.singletonList(
                                                                                Classroom.testBuilder().id(CLASSROOM_ID).build()
                                                                        ))
                                                                .build()
                                                )
                                        )
                                        .build()))
                .build();
    }
}
