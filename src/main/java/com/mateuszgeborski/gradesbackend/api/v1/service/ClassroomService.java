package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.ClassroomDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.TeacherDTOs;

public interface ClassroomService {

    ClassroomDTO save(ClassroomDTO classroomDTO);

    TeacherDTOs getTeachersOfClassroom(Long classroomId);
}
