package com.mateuszgeborski.gradesbackend.api.v1.model.dto.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.UserDTO;
import com.mateuszgeborski.gradesbackend.domain.message.MessageContainer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"owner"})
public class ReceivedMessageDTO extends MessageDTO {

    @JsonIgnoreProperties(value = {
            "motherName", "fatherName",
            "personalIdentityNum", "dateOfBirth",
            "user", "address", "contact", "profileImage"})
    private UserDTO sender;

    @Column(name = "messageContainer")
    private static final String messageContainer = MessageContainer.RECEIVED.get();

    @Builder
    public ReceivedMessageDTO(String title, String details, UserDTO owner, UserDTO sender) {
        super(title, details, owner);
        this.sender = sender;
    }
}
