package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.mapper.ClassroomMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.ClassroomDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.UserDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.SubjectDTOsWithUserDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.TeacherDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.repository.ClassroomRepository;
import com.mateuszgeborski.gradesbackend.domain.grades.Classroom;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ClassroomServiceImpl implements ClassroomService {

    private ClassroomMapper classroomMapper = ClassroomMapper.INSTANCE;

    private final ClassroomRepository classroomRepository;

    private final UserService userService;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository, UserService userService) {
        this.classroomRepository = classroomRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public ClassroomDTO save(ClassroomDTO classroomDTO) {
        Classroom classroom = classroomMapper.classroomDTOtoClassroom(classroomDTO);
        Classroom saved = classroomRepository.save(classroom);
        return classroomMapper.classroomToClassroomDTO(saved);
    }


    @Override
    public TeacherDTOs getTeachersOfClassroom(Long classroomId) {

        Optional<Classroom> optionalClassroom = classroomRepository.findById(classroomId);

        if (optionalClassroom.isPresent()){

            Classroom foundClassroom = optionalClassroom.get();

            Map<String, UserDTO> teachers = mapTeachersToSubjects(foundClassroom);

            List<SubjectDTOsWithUserDTOs> subjectWithUserDTOS = teachersMapToList(teachers);

            return TeacherDTOs.builder().subjectWithUserDTOS(subjectWithUserDTOS).build();
        }
        throw new ResourceNotFoundException("No classroom was found for id" + classroomId);
    }

    private List<SubjectDTOsWithUserDTOs> teachersMapToList(Map<String, UserDTO> teachers) {
        return teachers.entrySet().stream()
                .map(subject ->
                        SubjectDTOsWithUserDTOs.builder()
                                .subjectName(subject.getKey())
                                .userDTO(subject.getValue())
                                .build())
                .collect(Collectors.toList());
    }

    private Map<String, UserDTO> mapTeachersToSubjects(Classroom foundClassroom) {
        Long classroomId = foundClassroom.getId();
        Map<String, UserDTO> teachers = new HashMap<>();

        foundClassroom.getSubjects().forEach(
                classroomSubject -> classroomSubject.getTeachers().forEach(
                        classroomTeacher -> classroomTeacher.getSubjects().forEach(
                                teacherSubject -> teacherSubject.getClassrooms().forEach(
                                        teacherClassroom -> {
                                            if (teacherBelongsToClassroom(classroomId, teacherClassroom)) {
                                                UserDTO userDTO = userService.findById(classroomTeacher.getId());
                                                teachers.put(classroomSubject.getName(), userDTO);
                                            }
                                        }
                                )
                        )
                )
        );
        return teachers;
    }

    private boolean teacherBelongsToClassroom(Long classroomId, Classroom teacherClassroom) {
        return teacherClassroom.getId().equals(classroomId);
    }
}
