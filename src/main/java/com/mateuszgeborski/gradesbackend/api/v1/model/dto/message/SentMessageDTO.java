package com.mateuszgeborski.gradesbackend.api.v1.model.dto.message;

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
public class SentMessageDTO extends MessageDTO {

    private UserDTO receiver;

    @Column(name = "messageContainer")
    private static final String messageContainer = MessageContainer.SENT.get();

    @Builder
    public SentMessageDTO(String title, String details, UserDTO owner, UserDTO receiver) {
        super(title, details, owner);
        this.receiver = receiver;
    }
}
