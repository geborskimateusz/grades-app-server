package com.mateuszgeborski.gradesbackend.api.v1.repository;

import com.mateuszgeborski.gradesbackend.domain.user.details.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ProfileImage, Long> {
}
