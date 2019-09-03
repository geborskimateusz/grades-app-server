package com.mateuszgeborski.gradesbackend.domain.message;

import com.mateuszgeborski.gradesbackend.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "DeletedMessage" )
@Table(name = "deleted_message")
public class DeletedMessage extends Message {

    @ManyToOne
    @JoinColumn(name = "senderOrReceiver_id")
    private User senderOrReceiver;

    @Builder
    public DeletedMessage(String title, String details, User owner, User senderOrReceiver) {
        super(title, details, owner, MessageContainer.DELETED.get());
        this.senderOrReceiver = senderOrReceiver;
    }
}
