package com.helpers.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.ProfileImageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.UserDTO;
import com.mateuszgeborski.gradesbackend.domain.user.User;
import com.mateuszgeborski.gradesbackend.domain.user.details.ProfileImage;

public class ProfileImageMapperFactory {

    private static final String FAKE_IMG_URL = "fakeimgurl";
    private static final String FATHER_NAME = "John";

    public static ProfileImage getProfileImage() {
        return ProfileImage.builder()
                .imageUrl(FAKE_IMG_URL)
                .user(
                        User.testBuilder()
                                .fatherName(FATHER_NAME)
                                .build()
                ).build();
    }

    public static ProfileImageDTO getProfileImageDTO() {
        return ProfileImageDTO.builder()
                .imageUrl(FAKE_IMG_URL)
                .user(
                        UserDTO.testBuilder()
                                .fatherName(FATHER_NAME)
                                .build()
                ).build();
    }
}
