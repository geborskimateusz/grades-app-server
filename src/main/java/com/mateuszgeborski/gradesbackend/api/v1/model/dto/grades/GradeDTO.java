package com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.BaseEntityDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeDTO extends BaseEntityDTO {
    private String letter;
    private LocalDate dateOfIssue;
    private String topic;

    @JsonIgnore
    private StudentDTO student;

    @JsonIgnore
    private SubjectDTO subject;
}
