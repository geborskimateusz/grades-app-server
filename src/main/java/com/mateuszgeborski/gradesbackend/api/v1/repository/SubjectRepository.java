package com.mateuszgeborski.gradesbackend.api.v1.repository;

import com.mateuszgeborski.gradesbackend.domain.grades.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
