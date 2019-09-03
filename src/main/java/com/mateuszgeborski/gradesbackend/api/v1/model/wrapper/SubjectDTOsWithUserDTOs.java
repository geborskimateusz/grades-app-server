package com.mateuszgeborski.gradesbackend.api.v1.model.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubjectDTOsWithUserDTOs {
    @JsonProperty("subject")
    private String subjectName;

    @JsonProperty("teacher")
    @JsonIgnoreProperties({"user"})
    private UserDTO userDTO;
}
