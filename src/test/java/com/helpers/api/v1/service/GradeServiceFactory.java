package com.helpers.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.GradeDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.StudentDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.SubjectDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Grade;
import com.mateuszgeborski.gradesbackend.domain.grades.Student;
import com.mateuszgeborski.gradesbackend.domain.grades.Subject;

import java.time.LocalDate;

public class GradeServiceFactory {
    private static final long STUDENT_ID = 1L;
    private static final String SUBJECT_NAME = "Science";
    private static final String LETTER = "A";
    private static final String TOPIC = "For exam about ancient gods";
    private static final LocalDate DATE_OF_ISSUE = LocalDate.of(2015, 3, 2);

    public static StudentDTO getStudentDTO() {
        return StudentDTO.builder()
                .id(STUDENT_ID)
                .build();
    }

    public static SubjectDTO getSubjectDTO() {
        return SubjectDTO.builder()
                .name(SUBJECT_NAME)
                .build();
    }

    public static Grade getGrade() {
        return Grade.builder()
                .letter(LETTER)
                .topic(TOPIC)
                .dateOfIssue(DATE_OF_ISSUE)
                .student(getStudent())
                .subject(getSubject())
                .build();
    }

    public static Student getStudent() {
        return Student.builder()
                .id(STUDENT_ID)
                .build();
    }

    public static Subject getSubject() {
        return Subject.builder()
                .name(SUBJECT_NAME)
                .build();
    }

    public static GradeDTO getGradeDTO() {
        return GradeDTO.builder()
                .letter(LETTER)
                .topic(TOPIC)
                .dateOfIssue(DATE_OF_ISSUE)
                .student(getStudentDTO())
                .subject(getSubjectDTO())
                .build();
    }
}
