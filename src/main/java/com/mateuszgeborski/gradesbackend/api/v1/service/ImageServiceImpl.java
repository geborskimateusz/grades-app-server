package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.mapper.ProfileImageMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.ProfileImageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.repository.ImageRepository;
import com.mateuszgeborski.gradesbackend.domain.user.details.ProfileImage;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    private ProfileImageMapper profileImageMapper = ProfileImageMapper.INSTANCE;

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ProfileImageDTO save(ProfileImageDTO profileImageDTO)
    {
        ProfileImage profileImage = profileImageMapper.profileImageDTOtoProfileImage(profileImageDTO);
        ProfileImage saved = imageRepository.save(profileImage);
        return profileImageMapper.profileImageToProfileImageDTO(saved);
    }
}
