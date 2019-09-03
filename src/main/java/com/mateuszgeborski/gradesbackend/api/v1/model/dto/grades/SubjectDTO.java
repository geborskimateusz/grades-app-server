package com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.BaseEntityDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectDTO extends BaseEntityDTO {
    private String name;
    private List<ClassroomDTO> classrooms = new ArrayList<>();
    private List<GradeDTO> grades;
    private List<TeacherDTO> teachers = new ArrayList<>();
}
