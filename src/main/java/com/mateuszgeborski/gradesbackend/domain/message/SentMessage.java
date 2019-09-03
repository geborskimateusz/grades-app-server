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
@Entity(name = "SentMessage" )
@Table(name = "sent_message")
public class SentMessage extends Message {

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @Builder
    public SentMessage(String title, String details, User owner, User receiver) {
        super(title, details, owner, MessageContainer.SENT.get());
        this.receiver = receiver;
    }
}
