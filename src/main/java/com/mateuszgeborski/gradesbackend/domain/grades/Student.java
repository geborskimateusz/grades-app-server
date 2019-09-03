package com.mateuszgeborski.gradesbackend.domain.grades;

import com.mateuszgeborski.gradesbackend.domain.BaseEntity;
import com.mateuszgeborski.gradesbackend.domain.grades.Classroom;
import com.mateuszgeborski.gradesbackend.domain.grades.Grade;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Student")
@Table(name = "student")
public class Student {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Grade> grades;

}
