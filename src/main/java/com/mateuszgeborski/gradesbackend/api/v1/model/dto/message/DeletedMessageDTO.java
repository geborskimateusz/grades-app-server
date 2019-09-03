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
public class DeletedMessageDTO extends MessageDTO {

    UserDTO senderOrReceiver;

    @Column(name = "messageContainer")
    private static final String messageContainer = MessageContainer.DELETED.get();

    @Builder
    public DeletedMessageDTO(String title, String details, UserDTO owner, UserDTO senderOrReceiver) {
        super(title, details, owner);
        this.senderOrReceiver = senderOrReceiver;
    }
}
