package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.ClassroomDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.StudentDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.SubjectDTOsWithGradeDTOs;

public interface StudentService {

    StudentDTO save(StudentDTO studentDTO);

    SubjectDTOsWithGradeDTOs findAllSubjectsWithGradesByStudentId(Long id);

    ClassroomDTO getStudentClassroomByStudentId(Long studentId);
}
