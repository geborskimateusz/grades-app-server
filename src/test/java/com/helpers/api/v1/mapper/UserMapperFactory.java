package com.helpers.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.*;
import com.mateuszgeborski.gradesbackend.domain.user.details.Address;
import com.mateuszgeborski.gradesbackend.domain.user.details.Contact;
import com.mateuszgeborski.gradesbackend.domain.user.details.ProfileImage;
import com.mateuszgeborski.gradesbackend.domain.user.User;

import java.time.LocalDate;

public class UserMapperFactory {

    private static final String FAKE_FIRST_NAME = "Fake first name";
    private static final String FAKE_USERNAME = "Fake username";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.of(2000, 12, 12);
    private static final String FAKE_EMAIL = "fakeemail@gmail";
    private static final String FAKE_IMAGE_URL = "Fake image url";
    private static final String FAKE_CITY = "Fake city";

    public static User getUser() {
        return User.testBuilder()
                .firstName(FAKE_FIRST_NAME)
                .username(FAKE_USERNAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .contact(
                        Contact.builder()
                                .email(FAKE_EMAIL)
                                .build()
                )
                .profileImage(
                        ProfileImage.builder()
                                .imageUrl(FAKE_IMAGE_URL)
                                .build()
                )
                .address(
                        Address.builder()
                                .city(FAKE_CITY)
                                .build()
                )
                .build();
    }

    public static UserDTO getUserDTO() {
        return UserDTO.testBuilder()
                .firstName(FAKE_FIRST_NAME)
                .username(FAKE_USERNAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .contact(
                        ContactDTO.builder()
                                .email(FAKE_EMAIL)
                                .build()
                )
                .profileImage(
                        ProfileImageDTO.builder()
                                .imageUrl(FAKE_IMAGE_URL)
                                .build()
                )
                .address(
                        AddressDTO.builder()
                                .city(FAKE_CITY)
                                .build()
                )
                .build();
    }
}
