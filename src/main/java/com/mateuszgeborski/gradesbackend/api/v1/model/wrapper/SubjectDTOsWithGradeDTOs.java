package com.mateuszgeborski.gradesbackend.api.v1.model.wrapper;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubjectDTOsWithGradeDTOs {
    private List<SubjectDTOs> subjects;

}
