package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.mapper.TeacherMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.TeacherDTO;
import com.mateuszgeborski.gradesbackend.api.v1.repository.TeacherRepository;
import com.mateuszgeborski.gradesbackend.domain.grades.Teacher;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherMapper teacherMapper = TeacherMapper.INSTANCE;

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.teacherDTOtoTeacher(teacherDTO);
        Teacher saved = teacherRepository.save(teacher);
        return teacherMapper.teacherToTeacherDTO(saved);
    }
}
