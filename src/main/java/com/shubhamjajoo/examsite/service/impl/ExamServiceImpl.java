package com.shubhamjajoo.examsite.service.impl;

import com.shubhamjajoo.examsite.entity.Category;
import com.shubhamjajoo.examsite.entity.Exam;
import com.shubhamjajoo.examsite.exception.ResourceNotFoundException;
import com.shubhamjajoo.examsite.payload.response.DeleteResponse;
import com.shubhamjajoo.examsite.repository.CategoryRepository;
import com.shubhamjajoo.examsite.repository.ExamRepository;
import com.shubhamjajoo.examsite.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Exam readExam(Long id) {
        return examRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Exam", "id"));
    }

    @Override
    public Exam updateExam(Exam exam, Long id) {
        Exam oldExam = examRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Exam", "id"));

        Category oldCategory = categoryRepository.findById(exam.getCategory().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id"));

        oldExam.setId(id);
        oldExam.setTitle(exam.getTitle());
        oldExam.setDescription(exam.getDescription());
        oldExam.setHandle(exam.getHandle());
        oldExam.setExamCode(exam.getExamCode());
        oldExam.setCategory(oldCategory);

        return examRepository.save(oldExam);
    }

    @Override
    public DeleteResponse deleteExam(Long id) {
        Exam exam = examRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Exam", "id"));
        examRepository.delete(exam);
        return new DeleteResponse("Deleted Successfully", "Exam");
    }
}
