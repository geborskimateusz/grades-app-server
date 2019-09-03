package com.mateuszgeborski.gradesbackend.api.v1.model.wrapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.GradeDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Grade;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubjectDTOs {

    private String name;
    private List<GradeDTO> grades;
}

