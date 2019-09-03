package com.helpers.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.ClassroomDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.GradeDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.StudentDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.SubjectDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Classroom;
import com.mateuszgeborski.gradesbackend.domain.grades.Grade;
import com.mateuszgeborski.gradesbackend.domain.grades.Student;
import com.mateuszgeborski.gradesbackend.domain.grades.Subject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentServiceFactory {

    public static final long STUDENT_ID = 1L;
    private static final String CLASSROOM_LETTER = "1B";
    private static final String SCIENCE_SUBJECT = "Science";
    private static final String GRADE_A_LETTER = "A";
    private static final String GRADE_TOPIC = "Science Fake Topic";
    private static final String GRADE_B_LETTER = "B";
    private static final String SUBJECTDTO_HISTORY = "History";
    private static final LocalDate DATE_OF_ISSUE = LocalDate.of(1994, 1, 2);
    private static final String HISTORY_SUBJECT = "History";
    public static final int RANDOM_PAGE_VALUE = 1;


    public static StudentDTO getStudentDTO() {
        return StudentDTO.builder()
                .id(STUDENT_ID)
                .classroom(getClassroomDTO())
                .grades(
                        Stream.of(getHistoryGradesDTO(), getScienceGradesDTO())
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private static ClassroomDTO getClassroomDTO() {
        return ClassroomDTO.builder()
                .name(CLASSROOM_LETTER)
                .subjects(
                        Arrays.asList(getHistorySubjectDTO(), getScienceSubjectDTO())
                )
                .build();
    }

    private static SubjectDTO getScienceSubjectDTO() {
        return SubjectDTO.builder()
                .name(SCIENCE_SUBJECT)
                .build();
    }

    private static List<GradeDTO> getScienceGradesDTO() {
        return Stream.of(
                GradeDTO.builder()
                        .subject(getScienceSubjectDTO())
                        .dateOfIssue(DATE_OF_ISSUE)
                        .letter(GRADE_A_LETTER)
                        .topic(GRADE_TOPIC)
                        .build(),
                GradeDTO.builder()
                        .subject(getScienceSubjectDTO())
                        .dateOfIssue(DATE_OF_ISSUE)
                        .letter(GRADE_B_LETTER)
                        .topic(GRADE_TOPIC)
                        .build()
        ).collect(Collectors.toList());
    }

    private static SubjectDTO getHistorySubjectDTO() {
        return SubjectDTO.builder()
                .name(SUBJECTDTO_HISTORY)
                .build();
    }

    private static List<GradeDTO> getHistoryGradesDTO() {
        return Stream.of(
                GradeDTO.builder()
                        .subject(getHistorySubjectDTO())
                        .dateOfIssue(DATE_OF_ISSUE)
                        .letter(GRADE_A_LETTER)
                        .topic(GRADE_TOPIC)
                        .build(),
                GradeDTO.builder()
                        .subject(getHistorySubjectDTO())
                        .dateOfIssue(DATE_OF_ISSUE)
                        .letter(GRADE_A_LETTER)
                        .topic(GRADE_TOPIC)
                        .build(),
                GradeDTO.builder()
                        .subject(getHistorySubjectDTO())
                        .dateOfIssue(DATE_OF_ISSUE)
                        .letter(GRADE_A_LETTER)
                        .topic(GRADE_TOPIC)
                        .build()
        ).collect(Collectors.toList());
    }


    public static Student getStudent() {
        return Student.builder()
                .id(STUDENT_ID)
                .classroom(getClassroom())
                .grades(
                        Stream.of(getHistoryGrades(), getScienceGrades())
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private static Classroom getClassroom() {
        return Classroom.testBuilder()
                .name(CLASSROOM_LETTER)
                .subjects(
                        Arrays.asList(getHistorySubject(), getScienceSubject())
                )
                .build();
    }

    private static Subject getScienceSubject() {
        return Subject.builder()
                .name(SCIENCE_SUBJECT)
                .build();
    }

    private static List<Grade> getScienceGrades() {
        return Stream.of(
                Grade.builder()
                        .subject(getScienceSubject())
                        .dateOfIssue(LocalDate.of(1994, 1, 2))
                        .letter(GRADE_A_LETTER)
                        .topic(GRADE_TOPIC)
                        .build(),
                Grade.builder()
                        .subject(getScienceSubject())
                        .dateOfIssue(LocalDate.of(1994, 1, 2))
                        .letter(GRADE_B_LETTER)
                        .topic(GRADE_TOPIC)
                        .build()
        ).collect(Collectors.toList());
    }

    private static Subject getHistorySubject() {
        return Subject.builder()
                .name(HISTORY_SUBJECT)
                .build();
    }

    private static List<Grade> getHistoryGrades() {
        return Stream.of(
                Grade.builder()
                        .subject(getHistorySubject())
                        .dateOfIssue(DATE_OF_ISSUE)
                        .letter(GRADE_A_LETTER)
                        .topic(GRADE_TOPIC)
                        .build(),
                Grade.builder()
                        .subject(getHistorySubject())
                        .dateOfIssue(DATE_OF_ISSUE)
                        .letter(GRADE_B_LETTER)
                        .topic(GRADE_TOPIC)
                        .build(),
                Grade.builder()
                        .subject(getHistorySubject())
                        .dateOfIssue(DATE_OF_ISSUE)
                        .letter(GRADE_B_LETTER)
                        .topic(GRADE_TOPIC)
                        .build()
        ).collect(Collectors.toList());
    }

}
