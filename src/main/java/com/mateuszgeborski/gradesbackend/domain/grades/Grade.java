package com.mateuszgeborski.gradesbackend.domain.grades;

import com.mateuszgeborski.gradesbackend.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Grade")
@Table(name = "grade")
public class Grade extends BaseEntity {

    private String letter;

    private LocalDate dateOfIssue;

    private String topic;

    @ManyToOne(
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
