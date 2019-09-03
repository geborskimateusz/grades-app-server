package com.mateuszgeborski.gradesbackend.domain.grades;

import com.mateuszgeborski.gradesbackend.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Subject")
@Table(name = "subject")
public class Subject extends BaseEntity {

    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "classroom_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id")
    )
    private List<Classroom> classrooms = new ArrayList<>();

    @OneToMany(mappedBy = "subject",fetch = FetchType.LAZY)
    private List<Grade> grades;

    @ManyToMany
    @JoinTable(
            name = "subject_teacher",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teachers = new ArrayList<>();
}
