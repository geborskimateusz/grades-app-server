package com.mateuszgeborski.gradesbackend.api.v1.controller;

import com.helpers.api.v1.factory.StudentControllerFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.SubjectDTOsWithGradeDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StudentControllerTest {

    @Mock
    StudentService studentService;

    @InjectMocks
    StudentController studentController;

    MockMvc mockMvc;

    public StudentControllerTest() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController)
                .build();
    }

    @Test
    void getSubjectsWithGrades() throws Exception {

        SubjectDTOsWithGradeDTOs subjectsWithGrades = StudentControllerFactory.getSubjectsWithGrades();

        when(studentService.findAllSubjectsWithGradesByStudentId(anyLong())).thenReturn(subjectsWithGrades);


        mockMvc.perform(get(StudentControllerFactory.API_V_1_STUDENT_5_GRADES)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjects", hasSize(2)))
                .andExpect(jsonPath("$.subjects.[0].name", equalTo("Science")))
                .andExpect(jsonPath("$.subjects.[0].grades.[0].letter", equalTo("A")));
    }

    @Test
    void getSubjectsWithGradesNotFound() throws Exception {
        when(studentController.getSubjectsWithGrades(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(
                get(StudentControllerFactory.API_V_1_STUDENT_5_GRADES)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}