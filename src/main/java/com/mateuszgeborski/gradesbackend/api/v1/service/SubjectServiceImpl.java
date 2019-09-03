package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.mapper.SubjectMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.SubjectDTO;
import com.mateuszgeborski.gradesbackend.api.v1.repository.SubjectRepository;
import com.mateuszgeborski.gradesbackend.domain.grades.Subject;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectMapper subjectMapper = SubjectMapper.INSTANCE;

    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public SubjectDTO save(SubjectDTO subjectDTO) {
        Subject subject = subjectMapper.subjectDTOtoSubject(subjectDTO);
        Subject saved = subjectRepository.save(subject);
        return subjectMapper.subjectToSubjectDTO(saved);
    }
}
