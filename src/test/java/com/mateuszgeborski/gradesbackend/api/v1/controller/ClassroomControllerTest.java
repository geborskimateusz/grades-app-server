package com.mateuszgeborski.gradesbackend.api.v1.controller;

import com.helpers.api.v1.factory.ClassroomControllerFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.UserDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.TeacherDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.service.ClassroomService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClassroomControllerTest {

    public static final String $_TEACHERS = "$.teachers";
    public static final String $_FIRST_TEACHER = "$.teachers[0].";
    @Mock
    ClassroomService classroomService;

    @InjectMocks
    ClassroomController classroomController;

    MockMvc mockMvc;

    public ClassroomControllerTest() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(classroomController)
                .build();
    }

    @Test
    void getTeachersOfClassroom() throws Exception {
        TeacherDTOs expected = ClassroomControllerFactory.getTeacherDTOs();

        when(classroomService.getTeachersOfClassroom(anyLong())).thenReturn(expected);

        String subjectName = expected.getSubjectWithUserDTOS().get(0).getSubjectName();
        UserDTO userDTO = expected.getSubjectWithUserDTOS().get(0).getUserDTO();

        String firstJSONteacherUser = $_FIRST_TEACHER + "teacher.";
        mockMvc.perform(get(ClassroomControllerFactory.API_V_1_ID_TEACHERS)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath($_TEACHERS, hasSize(1)))
                .andExpect(jsonPath($_FIRST_TEACHER + "subject", equalTo(subjectName)))
                .andExpect(jsonPath(firstJSONteacherUser + "firstName", equalTo(userDTO.getFirstName())))
                .andExpect(jsonPath(firstJSONteacherUser + "lastName", equalTo(userDTO.getLastName())))
                .andExpect(jsonPath(firstJSONteacherUser + "address.street", equalTo(userDTO.getAddress().getStreet())));
    }

    @Test
    void getTeachersOfClassroomClassroomNotFound() throws Exception {
        when(classroomController.getTeachersOfClassroom(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(
                get(ClassroomControllerFactory.API_V_1_ID_TEACHERS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}