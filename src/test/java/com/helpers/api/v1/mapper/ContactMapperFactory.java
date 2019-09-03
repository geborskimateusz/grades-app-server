package com.helpers.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.*;
import com.mateuszgeborski.gradesbackend.domain.user.details.Address;
import com.mateuszgeborski.gradesbackend.domain.user.details.Contact;
import com.mateuszgeborski.gradesbackend.domain.user.details.ProfileImage;
import com.mateuszgeborski.gradesbackend.domain.user.User;

import java.time.LocalDate;

public class ContactMapperFactory {

    public static final String FAKE_EMAIL = "fake@gmail.com";
    public static final String PHONE_NUMBER = "511999222";
    public static final String FAKE_USERNAME = "fakeusername";
    public static final LocalDate DATE_OF_BIRTH = LocalDate.of(2000, 12, 12);
    public static final String FAKE_IMAGE_URL = "fake image url";
    public static final String FAKE_CITY = "fakecity";

    public  static Contact getContact() {
        return Contact.builder()
                .email(FAKE_EMAIL)
                .phoneNumber(PHONE_NUMBER)
                .user(
                        User.testBuilder()
                                .username(FAKE_USERNAME)
                                .dateOfBirth(DATE_OF_BIRTH)
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
                                .build()
                )
                .build();
    }

    public static ContactDTO getContactDTO() {
        return ContactDTO.builder()
                .email(FAKE_EMAIL)
                .phoneNumber(PHONE_NUMBER)
                .user(
                        UserDTO.testBuilder()
                                .username(FAKE_USERNAME)
                                .dateOfBirth(DATE_OF_BIRTH)
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
                                .build()
                )
                .build();
    }
}
