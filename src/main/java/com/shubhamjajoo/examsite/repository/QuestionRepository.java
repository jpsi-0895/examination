package com.shubhamjajoo.examsite.repository;

import com.shubhamjajoo.examsite.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
