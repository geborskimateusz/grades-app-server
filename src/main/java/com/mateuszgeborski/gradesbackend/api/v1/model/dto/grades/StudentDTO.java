package com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private ClassroomDTO classroom;
    private List<GradeDTO> grades;

}
