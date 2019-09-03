package com.mateuszgeborski.gradesbackend.api.v1.model.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class TeacherDTOs {

   @JsonProperty("teachers")
   List<SubjectDTOsWithUserDTOs> subjectWithUserDTOS = new ArrayList<>();
}
