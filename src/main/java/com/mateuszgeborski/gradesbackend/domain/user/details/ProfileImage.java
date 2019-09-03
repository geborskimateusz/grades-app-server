package com.mateuszgeborski.gradesbackend.domain.user.details;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mateuszgeborski.gradesbackend.domain.BaseEntity;
import com.mateuszgeborski.gradesbackend.domain.user.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ProfileImage")
@Table(name = "profile_image")
@JsonIgnoreProperties(value = { "user" })
public class ProfileImage extends BaseEntity {
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
