package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.TeacherDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Teacher;

public interface TeacherService {
    TeacherDTO save(TeacherDTO teacherDTO);
}
