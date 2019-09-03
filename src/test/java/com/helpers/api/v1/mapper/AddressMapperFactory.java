package com.helpers.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.*;
import com.mateuszgeborski.gradesbackend.domain.user.User;
import com.mateuszgeborski.gradesbackend.domain.user.details.Address;
import com.mateuszgeborski.gradesbackend.domain.user.details.Contact;
import com.mateuszgeborski.gradesbackend.domain.user.details.ProfileImage;

public class AddressMapperFactory {

    private static final String FAKE_CITY = "Fake city";
    private static final String FAKE_STREET = "Fake street";
    private static final String HOME_NUMBER = "2";
    private static final String POSTAL_CODE = "22-222";
    private static final String FAKE_NAME = "Fake name";
    private static final String FAKE_USERNAME = "Fake username";
    private static final String FAKE_EMAIL = "fake@email";
    private static final String FAKE_IMAGE_URL = "fake image url";

    public static Address getAddress() {
        return Address.builder()
                .city(FAKE_CITY)
                .street(FAKE_STREET)
                .homeNumber(HOME_NUMBER)
                .postalCode(POSTAL_CODE)
                .user(
                        User.testBuilder()
                                .fatherName(FAKE_NAME)
                                .username(FAKE_USERNAME)
                                .contact(
                                        Contact.builder()
                                                .email(FAKE_EMAIL)
                                                .build()
                                )
                                .profileImage(
                                        ProfileImage.builder()
                                                .imageUrl(FAKE_IMAGE_URL)
                                                .build()
                                ).
                                build()
                )
                .build();
    }

    public static AddressDTO getAddressDTO() {
        return AddressDTO.builder()
                .city(FAKE_CITY)
                .street(FAKE_STREET)
                .homeNumber(HOME_NUMBER)
                .postalCode(POSTAL_CODE)
                .user(
                        UserDTO.testBuilder()
                                .fatherName(FAKE_NAME)
                                .username(FAKE_USERNAME)
                                .contact(
                                        ContactDTO.builder()
                                                .email(FAKE_EMAIL)
                                                .build()
                                )
                                .profileImage(
                                        ProfileImageDTO.builder()
                                                .imageUrl(FAKE_IMAGE_URL)
                                                .build()
                                ).
                                build()
                )
                .build();
    }
}
