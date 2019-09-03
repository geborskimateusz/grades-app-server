package com.mateuszgeborski.gradesbackend.api.v1.model.dto.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.BaseEntityDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.UserDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO extends BaseEntityDTO {
    private String title;
    private String details;
    private LocalDate dateOfSending;

    @JsonIgnoreProperties(value = {
            "motherName", "fatherName",
            "personalIdentityNum", "dateOfBirth",
            "user", "address", "contact", "profileImage"})
    @JsonProperty(value = "userDetails")
    private UserDTO owner;

    public MessageDTO(String title, String details, UserDTO owner) {
        this.title = title;
        this.details = details;
        this.dateOfSending = LocalDate.now();
        this.owner = owner;
    }

}
