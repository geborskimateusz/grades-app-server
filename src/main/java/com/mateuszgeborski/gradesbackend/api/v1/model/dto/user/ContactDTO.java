package com.mateuszgeborski.gradesbackend.api.v1.model.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.BaseEntityDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"user"})
public class ContactDTO extends BaseEntityDTO {
    private String phoneNumber;
    private String email;
    private UserDTO user;
}

