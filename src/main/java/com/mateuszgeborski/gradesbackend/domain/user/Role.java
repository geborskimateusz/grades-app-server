package com.mateuszgeborski.gradesbackend.domain.user;

import com.mateuszgeborski.gradesbackend.domain.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Role")
@Table(name = "role")
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

}