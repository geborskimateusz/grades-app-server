package com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.BaseEntityDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"students", "subjects"})
public class ClassroomDTO extends BaseEntityDTO {
    private String name;

    private List<StudentDTO> students = new ArrayList<>();

    private List<SubjectDTO> subjects = new ArrayList<>();

}
