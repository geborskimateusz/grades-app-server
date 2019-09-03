package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.GradeDTO;
import com.mateuszgeborski.gradesbackend.domain.grades.Grade;

import java.util.List;

public interface GradeService {
    GradeDTO save(GradeDTO grade);
    List<GradeDTO> saveAll(List<GradeDTO> grades);
}
