package com.shubhamjajoo.examsite.service;

import com.shubhamjajoo.examsite.entity.Exam;
import com.shubhamjajoo.examsite.payload.response.DeleteResponse;

public interface ExamService {
    public Exam createExam(Exam exam);

    public Exam readExam(Long id);

    public Exam updateExam(Exam exam, Long id);

    public DeleteResponse deleteExam(Long id);
}
