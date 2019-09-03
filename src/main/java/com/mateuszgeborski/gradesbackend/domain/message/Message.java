package com.mateuszgeborski.gradesbackend.domain.message;

import com.mateuszgeborski.gradesbackend.domain.BaseEntity;
import com.mateuszgeborski.gradesbackend.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class Message extends BaseEntity {
    private String title;
    private String details;
    private LocalDate dateOfSending;

    @Column(name = "messageContainer")
    private String messageContainer;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Message(String title, String details, User owner, String messageContainer) {
        this.title = title;
        this.details = details;
        this.dateOfSending = LocalDate.now();
        this.owner = owner;
        this.messageContainer = messageContainer;
    }

}
