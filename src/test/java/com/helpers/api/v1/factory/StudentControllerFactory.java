package com.helpers.api.v1.factory;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.GradeDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.SubjectDTOsWithGradeDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.SubjectDTOs;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentControllerFactory{

        public static final String API_V_1_STUDENT_5_GRADES = "/api/v1/student/5/grades";

        public static SubjectDTOsWithGradeDTOs getSubjectsWithGrades() {
            return SubjectDTOsWithGradeDTOs.builder()
                    .subjects(getSubjects())
                    .build();
        }


        private static List<SubjectDTOs> getSubjects() {
            return Stream.of(
                    SubjectDTOs.builder()
                            .name("Science")
                            .grades(getScienceGradesDTO())
                            .build(),
                    SubjectDTOs.builder()
                            .name("History")
                            .grades(getHistoryGradesDTO())
                            .build()
            ).collect(Collectors.toList());
        }

        private static List<GradeDTO> getScienceGradesDTO() {
            return Stream.of(
                    GradeDTO.builder()
                            .dateOfIssue(LocalDate.of(1994, 1, 2))
                            .letter("A")
                            .topic("Science Fake Topic")
                            .build(),
                    GradeDTO.builder()
                            .dateOfIssue(LocalDate.of(1994, 1, 2))
                            .letter("B")
                            .topic("Science Fake2 Topic")
                            .build()
            ).collect(Collectors.toList());
        }

        private static List<GradeDTO> getHistoryGradesDTO() {
            return Stream.of(
                    GradeDTO.builder()
                            .dateOfIssue(LocalDate.of(1994, 1, 2))
                            .letter("A")
                            .topic("History Fake Topic")
                            .build(),
                    GradeDTO.builder()
                            .dateOfIssue(LocalDate.of(1994, 1, 2))
                            .letter("B")
                            .topic("History Fake2 Topic")
                            .build(),
                    GradeDTO.builder()
                            .dateOfIssue(LocalDate.of(1994, 1, 2))
                            .letter("C")
                            .topic("History Fake3 Topic")
                            .build()
            ).collect(Collectors.toList());
        }
}
