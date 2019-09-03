package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.helpers.api.v1.service.StudentServiceFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.StudentDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.SubjectDTOsWithGradeDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.SubjectDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.repository.StudentRepository;
import com.mateuszgeborski.gradesbackend.domain.grades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class StudentServiceImplTest {


    Long studentId = StudentServiceFactory.STUDENT_ID;

    StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentServiceImpl(studentRepository);
    }

    @Test
    void save() {
        Student student = StudentServiceFactory.getStudent();
        StudentDTO expected = StudentServiceFactory.getStudentDTO();

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDTO actual = studentService.save(expected);

        assertAll("Should create new Student and return it back as StudentDTO",
                () -> {
                    assertEquals(expected.getId(), actual.getId());
                    assertEquals(expected.getClassroom().getName(), actual.getClassroom().getName());
                    assertEquals(
                            expected.getClassroom().getSubjects().get(0).getName(),
                            actual.getClassroom().getSubjects().get(0).getName());
                    assertEquals(expected.getGrades().get(0).getLetter(), actual.getGrades().get(0).getLetter());
                });

    }

    @Test
    void findAllSubjectsWithGradesByStudentId() {
        Student studentFound = StudentServiceFactory.getStudent();

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(studentFound));

        SubjectDTOsWithGradeDTOs subjectsWithGrades =
                studentService.findAllSubjectsWithGradesByStudentId(studentId);

        List<SubjectDTOs> wrapperSubjects = subjectsWithGrades.getSubjects();

        assertAll(
                "Should check if there is correct size of subjects " +
                        "and values like title or letter from given grades are correct",
                () -> {
                    assertEquals(wrapperSubjects.size(), 2);
                    assertEquals(wrapperSubjects.get(0).getGrades().size(), 3);
                    assertEquals(wrapperSubjects.get(0).getName(), "History");
                    assertEquals(wrapperSubjects.get(1).getGrades().size(), 2);
                    assertEquals(wrapperSubjects.get(1).getName(), "Science");
                    assertEquals(wrapperSubjects.get(1).getGrades().get(1).getLetter(), "B");
                }
        );
    }

    @Test
    void findAllSubjectsWithGradesByStudentIdThrowException() {

        when(studentRepository.findById(anyLong()))
                .thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> {
            studentService.findAllSubjectsWithGradesByStudentId(studentId);
        });
    }
}