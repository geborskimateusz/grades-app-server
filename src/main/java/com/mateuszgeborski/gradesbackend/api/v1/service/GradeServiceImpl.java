package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.mapper.GradeMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.GradeDTO;
import com.mateuszgeborski.gradesbackend.api.v1.repository.GradeRepository;
import com.mateuszgeborski.gradesbackend.domain.grades.Grade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private GradeMapper gradeMapper = GradeMapper.INSTANCE;

    private final GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public GradeDTO save(GradeDTO gradeDTO) {
        Grade grade = gradeMapper.gradeDTOtoGrade(gradeDTO);
        Grade saved = gradeRepository.save(grade);
        return gradeMapper.gradeToGradeDTO(saved);
    }

    @Transactional
    @Override
    public List<GradeDTO> saveAll(List<GradeDTO> gradeDTOS) {
        return gradeDTOS.stream()
                .map(gradeDTO -> gradeMapper.gradeDTOtoGrade(gradeDTO))
                .map(gradeRepository::save)
                .map(grade -> gradeMapper.gradeToGradeDTO(grade))
                .collect(Collectors.toList());
    }
}
