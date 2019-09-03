package com.helpers.api.v1.factory;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.*;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.SubjectDTOsWithUserDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.TeacherDTOs;

import java.time.LocalDate;
import java.util.Collections;

public class ClassroomControllerFactory {

    private static final String API_V_1_CLASSROOM = "/api/v1/classroom/";
    private static final String CLASSROOM_ID = "5";
    public static final String API_V_1_ID_TEACHERS = API_V_1_CLASSROOM + CLASSROOM_ID + "/teachers";
    private static final String SUBJECT_NAME = "History";

    public static TeacherDTOs getTeacherDTOs() {
        return TeacherDTOs.builder()
                .subjectWithUserDTOS(
                        Collections.singletonList(
                                SubjectDTOsWithUserDTOs.builder()
                                        .subjectName(SUBJECT_NAME)
                                        .userDTO(
                                                getUserDTO()
                                        ).build()
                        )
                )
                .build();
    }


    private static UserDTO getUserDTO() {
        return UserDTO.testBuilder()
                .firstName("Fake first name")
                .user(
                        UserDTO.defaultBuilder()
                                .username("Fake username")
                                .build()
                )
                .dateOfBirth(LocalDate.of(2000, 12, 12))
                .contact(
                        ContactDTO.builder()
                                .email("fakeemail@gmail")
                                .build()
                )
                .profileImage(
                        ProfileImageDTO.builder()
                                .imageUrl("Fake image url").build()
                )
                .address(
                        AddressDTO.builder()
                                .city("Fake city")
                                .build()
                )
                .build();
    }
}
