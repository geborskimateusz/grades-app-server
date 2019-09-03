package com.mateuszgeborski.gradesbackend.domain.user.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mateuszgeborski.gradesbackend.domain.BaseEntity;
import com.mateuszgeborski.gradesbackend.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Address")
@Table(name = "address")
@JsonIgnoreProperties(value = { "user" })
public class Address extends BaseEntity {

    private String city;
    private String street;
    private String homeNumber;
    private String postalCode;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
