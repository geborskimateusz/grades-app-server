package com.mateuszgeborski.gradesbackend.api.v1.controller;

import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.TeacherDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.service.ClassroomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "${cors.origin.value}")
@RestController
@RequestMapping(ClassroomController.API_V_1_CLASSROOM)
public class ClassroomController {
    public static final String API_V_1_CLASSROOM = "api/v1/classroom";

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping(value = "{classroomId}/teachers")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDTOs getTeachersOfClassroom(@PathVariable Long classroomId){
        return classroomService.getTeachersOfClassroom(classroomId);
    }
}
