package com.shubhamjajoo.examsite.repository;

import com.shubhamjajoo.examsite.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
