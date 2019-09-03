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
@Entity(name = "ReceivedMessage")
@Table(name = "received_message")
public class ReceivedMessage extends Message {

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @Builder
    public ReceivedMessage(String title, String details, User owner, User sender) {
        super(title, details, owner, MessageContainer.RECEIVED.get());
        this.sender = sender;
    }
}
