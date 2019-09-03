package com.helpers.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.GradeDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.StudentDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.SubjectDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Grade;
import com.mateuszgeborski.gradesbackend.domain.grades.Student;
import com.mateuszgeborski.gradesbackend.domain.grades.Subject;

import java.time.LocalDate;

public class GradeMapperFactory {

    private static final String LETTER = "A";
    private static final String SUBJECT_NAME = "History";
    private static final String FAKE_TOPIC = "Fake topic";
    private static final LocalDate DATE_OF_ISSUE = LocalDate.of(2000, 12, 12);
    private static final long STUDENT_ID = 1L;

    public static Grade getGrade() {
        return Grade.builder()
                .letter(LETTER)
                .subject(
                        Subject.builder()
                                .name(SUBJECT_NAME)
                                .build()
                )
                .topic(FAKE_TOPIC)
                .dateOfIssue(DATE_OF_ISSUE)
                .student(
                        Student.builder()
                                .id(STUDENT_ID)
                                .build()
                )
                .build();
    }

    public static GradeDTO getGradeDTO() {
        return GradeDTO.builder()
                .letter(LETTER)
                .subject(
                        SubjectDTO.builder()
                                .name(SUBJECT_NAME)
                                .build()
                )
                .topic(FAKE_TOPIC)
                .dateOfIssue(DATE_OF_ISSUE)
                .student(
                        StudentDTO.builder()
                                .id(STUDENT_ID)
                                .build()
                )
                .build();
    }
}
