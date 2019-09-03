package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.helpers.api.v1.mapper.EntityStringJoiner;
import com.helpers.api.v1.mapper.ProfileImageMapperFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.ProfileImageDTO;
import com.mateuszgeborski.gradesbackend.domain.user.details.ProfileImage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileImageMapperTest {

    ProfileImageMapper profileImageMapper = ProfileImageMapper.INSTANCE;

    @Test
    void profileImageDTOtoProfileImage() {
        ProfileImageDTO expected = ProfileImageMapperFactory.getProfileImageDTO();

        ProfileImage acual = profileImageMapper.profileImageDTOtoProfileImage(expected);

        assertEquals(
                expected.getImageUrl() + "," +
                        expected.getUser().getFatherName(),
                EntityStringJoiner.mergeProfileImageToString(acual)
        );
    }

    @Test
    void profileImageToProfileImageDTO() {
        ProfileImage expected = ProfileImageMapperFactory.getProfileImage();

        ProfileImageDTO actual = profileImageMapper.profileImageToProfileImageDTO(expected);

        assertEquals(
                EntityStringJoiner.mergeProfileImageToString(expected),
                actual.getImageUrl() + "," +
                        actual.getUser().getFatherName()
        );
    }
}