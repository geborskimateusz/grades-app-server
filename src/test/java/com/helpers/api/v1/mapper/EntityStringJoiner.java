package com.helpers.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.domain.grades.*;
import com.mateuszgeborski.gradesbackend.domain.user.details.Address;
import com.mateuszgeborski.gradesbackend.domain.user.details.Contact;
import com.mateuszgeborski.gradesbackend.domain.user.details.ProfileImage;
import com.mateuszgeborski.gradesbackend.domain.user.User;

import java.util.StringJoiner;

public class EntityStringJoiner {

    public static final String DELIMITER = ",";

    public static String mergeAddressDataToString(Address address) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        stringJoiner.add(address.getCity());
        stringJoiner.add(address.getUser().getFirstName());
        stringJoiner.add(address.getUser().getContact().getEmail());
        stringJoiner.add(address.getUser().getProfileImage().getImageUrl());

        return stringJoiner.toString();
    }

    public static String mergeClassroomDataToString(Classroom classroom) {
        String firstSubjectName = classroom.getSubjects().get(0).getName();
        Long firstStudentId = classroom.getStudents().get(0).getId();
        int numberOfSubjectsFromClassroom = classroom.getSubjects().size();

        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        stringJoiner.add(classroom.getName());
        stringJoiner.add(String.valueOf(numberOfSubjectsFromClassroom));
        stringJoiner.add(firstSubjectName);
        stringJoiner.add(String.valueOf(firstStudentId));

        return stringJoiner.toString();
    }

    public static String mergeContactToString(Contact contact) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        stringJoiner.add(contact.getEmail());
        stringJoiner.add(contact.getUser().getDateOfBirth().toString());
        stringJoiner.add(contact.getUser().getProfileImage().getImageUrl());

        return stringJoiner.toString();
    }

    public static String mergeGradeToString(Grade grade) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        stringJoiner.add(grade.getLetter());

        return stringJoiner.toString();
    }

    public static String mergeProfileImageToString(ProfileImage profileImage) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        stringJoiner.add(profileImage.getImageUrl());
        stringJoiner.add(profileImage.getUser().getFatherName());

        return stringJoiner.toString();
    }

    public static String mergeStudentToString(Student student) {
        String firstGradeLetter = student.getGrades().get(0).getLetter();

        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        stringJoiner.add(String.valueOf(student.getId()));
        stringJoiner.add(student.getClassroom().getName());
        stringJoiner.add(firstGradeLetter);

        return stringJoiner.toString();
    }

    public static String mergeSubjectToString(Subject subject) {
        int numberOfSubjectClassrooms = subject.getClassrooms().size();
        String firstGradeLetter = subject.getGrades().get(0).getLetter();
        Long firstTeacherId = subject.getTeachers().get(0).getId();

        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        stringJoiner.add(subject.getName());
        stringJoiner.add(String.valueOf(numberOfSubjectClassrooms));
        stringJoiner.add(firstGradeLetter);
        stringJoiner.add(String.valueOf(firstTeacherId));

        return stringJoiner.toString();
    }

    public static String mergeTeacherToString(Teacher teacher) {
        String firstSubjectName = teacher.getSubjects().get(0).getName();

        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        stringJoiner.add(String.valueOf(teacher.getId()));
        stringJoiner.add(firstSubjectName);

        return stringJoiner.toString();
    }


    public static String mergeUserToString(User user) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        stringJoiner.add(user.getFirstName());
        stringJoiner.add(user.getContact().getEmail());
        stringJoiner.add(user.getProfileImage().getImageUrl());
        stringJoiner.add(user.getAddress().getCity());
        stringJoiner.add(user.getDateOfBirth().toString());

        return stringJoiner.toString();
    }


}
