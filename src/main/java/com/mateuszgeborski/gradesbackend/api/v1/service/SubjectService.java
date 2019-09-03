package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.SubjectDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Subject;

public interface SubjectService {
    SubjectDTO save(SubjectDTO subjectDTO);
}
