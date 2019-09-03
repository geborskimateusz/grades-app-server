package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.helpers.api.v1.service.GradeServiceFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.GradeDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.StudentDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.SubjectDTO;
import com.mateuszgeborski.gradesbackend.api.v1.repository.GradeRepository;
import com.mateuszgeborski.gradesbackend.domain.grades.Grade;
import com.mateuszgeborski.gradesbackend.domain.grades.Student;
import com.mateuszgeborski.gradesbackend.domain.grades.Subject;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GradeServiceImplTest {

    GradeService gradeService;

    @Mock
    GradeRepository gradeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        gradeService = new GradeServiceImpl(gradeRepository);
    }

    @Test
    void save() {
        Grade grade = GradeServiceFactory.getGrade();
        GradeDTO expected = GradeServiceFactory.getGradeDTO();

        when(gradeRepository.save(any(Grade.class))).thenReturn(grade);

        GradeDTO actual = gradeService.save(expected);

        assertAll("Should create new Grade and return it back",
                () ->
                {
                    assertEquals(expected.getId(), actual.getId());
                    assertEquals(expected.getLetter(), actual.getLetter());
                    assertEquals(expected.getTopic(), actual.getTopic());
                    assertEquals(expected.getDateOfIssue(), actual.getDateOfIssue());
                }
        );
    }
}