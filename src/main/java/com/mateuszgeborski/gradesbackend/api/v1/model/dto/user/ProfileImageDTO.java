package com.mateuszgeborski.gradesbackend.api.v1.model.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.BaseEntityDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"user"})
public class ProfileImageDTO extends BaseEntityDTO {
    private String imageUrl;
    private UserDTO user;
}
