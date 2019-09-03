package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.helpers.api.v1.service.ClassroomServiceFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.ClassroomDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.TeacherDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.repository.ClassroomRepository;
import com.mateuszgeborski.gradesbackend.domain.grades.Classroom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ClassroomServiceImplTest {


    ClassroomService classroomService;

    @Mock
    ClassroomRepository classroomRepository;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        classroomService = new ClassroomServiceImpl(classroomRepository, userService);
    }

    @Test
    void save() {
        Classroom classroom = ClassroomServiceFactory.getClassroom();
        ClassroomDTO expected = ClassroomServiceFactory.getClassroomDTO();

        when(classroomRepository.save(any(Classroom.class))).thenReturn(classroom);

        ClassroomDTO actual = classroomService.save(expected);

        assertAll(
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getSubjects().size(), actual.getSubjects().size()),
                () -> assertEquals(expected.getStudents().size(), actual.getStudents().size()));
    }

    @Test
    void getTeachersOfClassroom() {
        Classroom expected = ClassroomServiceFactory.getClassroom();

        when(classroomRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        TeacherDTOs actual = classroomService.getTeachersOfClassroom(ClassroomServiceFactory.CLASSROOM_ID);

        assertEquals(
                expected.getSubjects().get(0).getName(),
                actual.getSubjectWithUserDTOS().get(0).getSubjectName());
    }


}