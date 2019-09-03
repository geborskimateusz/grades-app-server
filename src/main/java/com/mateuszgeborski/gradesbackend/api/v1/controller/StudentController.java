package com.mateuszgeborski.gradesbackend.api.v1.controller;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.grades.ClassroomDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.UserDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.SubjectDTOsWithGradeDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.service.StudentService;
import com.mateuszgeborski.gradesbackend.api.v1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "${cors.origin.value}")
@RestController
@RequestMapping(StudentController.API_V_1_STUDENT)
public class StudentController {

    public static final String API_V_1_STUDENT = "api/v1/student";

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/{studentId}/grades")
    @ResponseStatus(HttpStatus.OK)
    public SubjectDTOsWithGradeDTOs getSubjectsWithGrades(@PathVariable Long studentId){
        return studentService.findAllSubjectsWithGradesByStudentId(studentId);
    }

    @GetMapping(value = "/{studentId}/classroom")
    @ResponseStatus(HttpStatus.OK)
    public ClassroomDTO getClassroom(@PathVariable Long studentId){
        return studentService.getStudentClassroomByStudentId(studentId);
    }

}
