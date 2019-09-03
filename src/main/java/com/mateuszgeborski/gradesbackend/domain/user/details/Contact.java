package com.mateuszgeborski.gradesbackend.domain.user.details;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mateuszgeborski.gradesbackend.domain.BaseEntity;
import com.mateuszgeborski.gradesbackend.domain.user.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Contact")
@Table(name = "contact")
@JsonIgnoreProperties(value = { "user" })
public class Contact extends BaseEntity {

    private String phoneNumber;
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
