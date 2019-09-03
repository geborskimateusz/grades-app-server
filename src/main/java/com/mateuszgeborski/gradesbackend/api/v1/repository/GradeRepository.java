package com.mateuszgeborski.gradesbackend.api.v1.repository;

import com.mateuszgeborski.gradesbackend.domain.grades.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {
    List<Grade> findByStudentId(Long id);
}
