package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.mapper.ClassroomMapper;
import com.mateuszgeborski.gradesbackend.api.v1.mapper.GradeMapper;
import com.mateuszgeborski.gradesbackend.api.v1.mapper.StudentMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.ClassroomDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.GradeDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.StudentDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.SubjectDTOsWithGradeDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.SubjectDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.repository.StudentRepository;
import com.mateuszgeborski.gradesbackend.domain.grades.Grade;
import com.mateuszgeborski.gradesbackend.domain.grades.Student;
import com.mateuszgeborski.gradesbackend.domain.grades.Subject;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentMapper studentMapper = StudentMapper.INSTANCE;
    private GradeMapper gradeMapper = GradeMapper.INSTANCE;
    private ClassroomMapper classroomMapper = ClassroomMapper.INSTANCE;

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        Student student = studentMapper.studentDTOtoStudent(studentDTO);
        Student saved = studentRepository.save(student);
        return studentMapper.studentToStudentDTO(saved);
    }

    @Override
    public SubjectDTOsWithGradeDTOs findAllSubjectsWithGradesByStudentId(Long id) {

        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student studentFound = optionalStudent.get();

            List<SubjectDTOs> subjectsWrappers = new ArrayList<>();
            SubjectDTOsWithGradeDTOs wrapper = new SubjectDTOsWithGradeDTOs(subjectsWrappers);
            List<Grade> grades = studentFound.getGrades();
            List<Subject> subjects = studentFound.getClassroom().getSubjects();

            subjects.forEach(subject -> {
                List<GradeDTO> subjectWrapperGrades = new ArrayList<>();
                grades.forEach(grade -> {
                    if (grade.getSubject().getName().equals(subject.getName())) {
                        GradeDTO gradeDTO = gradeMapper.gradeToGradeDTO(grade);
                        subjectWrapperGrades.add(gradeDTO);
                    }
                });

                SubjectDTOs subjectsWrapper = new SubjectDTOs(subject.getName(), subjectWrapperGrades);
                subjectsWrappers.add(subjectsWrapper);
            });

            return wrapper;

        } else {
            throw new ResourceNotFoundException("Cannot found student of given id!");
        }
    }

    @Override
    public ClassroomDTO getStudentClassroomByStudentId(Long studentId) {
        return studentRepository.findById(studentId)
                .map(Student::getClassroom)
                .map(classroom -> classroomMapper.classroomToClassroomDTOwithoutCollections(classroom))
                .orElseThrow(ResourceNotFoundException::new);
    }
}
