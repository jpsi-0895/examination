package com.shubhamjajoo.examsite.repository;

import com.shubhamjajoo.examsite.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
