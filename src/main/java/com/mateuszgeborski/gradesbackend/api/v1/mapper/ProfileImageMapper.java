package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.ProfileImageDTO;
import com.mateuszgeborski.gradesbackend.domain.user.details.ProfileImage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileImageMapper {

    ProfileImageMapper INSTANCE = Mappers.getMapper(ProfileImageMapper.class);

    ProfileImageDTO profileImageToProfileImageDTO(ProfileImage profileImage);

    ProfileImage profileImageDTOtoProfileImage(ProfileImageDTO profileImageDTO);

}
