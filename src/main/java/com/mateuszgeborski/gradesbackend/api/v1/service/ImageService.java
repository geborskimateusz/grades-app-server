package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.ProfileImageDTO;

public interface ImageService {

    ProfileImageDTO save(ProfileImageDTO profileImageDTO);
}
