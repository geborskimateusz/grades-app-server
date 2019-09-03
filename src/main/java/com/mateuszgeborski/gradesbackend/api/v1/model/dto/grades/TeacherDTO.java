package com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDTO {
    private Long id;
    private List<SubjectDTO> subjects = new ArrayList<>();
}
