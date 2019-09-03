package com.mateuszgeborski.gradesbackend.domain.grades;

import com.mateuszgeborski.gradesbackend.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Classroom")
@Table(name = "classroom")
public class Classroom extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "classroom",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    private List<Student> students = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "classroom_subject",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects = new ArrayList<>();

    @Builder(builderMethodName = "defaultBuilder")
    public Classroom(String name, List<Student> students, List<Subject> subjects) {
        this.name = name;
        this.students = students;
        this.subjects = subjects;
    }

    @Builder(builderMethodName = "testBuilder", builderClassName = "ClassroomTestBuilder")
    public Classroom(Long id, String name, List<Student> students, List<Subject> subjects) {
        super(id);
        this.name = name;
        this.students = students;
        this.subjects = subjects;
    }
}
