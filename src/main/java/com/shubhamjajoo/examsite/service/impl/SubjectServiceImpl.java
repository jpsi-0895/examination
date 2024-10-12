package com.shubhamjajoo.examsite.service.impl;

import com.shubhamjajoo.examsite.entity.Category;
import com.shubhamjajoo.examsite.entity.Subject;
import com.shubhamjajoo.examsite.exception.ResourceNotFoundException;
import com.shubhamjajoo.examsite.payload.response.DeleteResponse;
import com.shubhamjajoo.examsite.repository.CategoryRepository;
import com.shubhamjajoo.examsite.repository.SubjectRepository;
import com.shubhamjajoo.examsite.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject readSubject(Long id) {
        return subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject", "id"));
    }

    @Override
    public Subject updateSubject(Subject subject, Long id) {
        Subject oldSubject = subjectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Subject", "id"));

        Category oldCategory = categoryRepository.findById(subject.getCategory().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id"));

        oldSubject.setId(id);
        oldSubject.setTitle(subject.getTitle());
        oldSubject.setDescription(subject.getDescription());
        oldSubject.setHandle(subject.getHandle());
        oldSubject.setCategory(oldCategory);

        return subjectRepository.save(oldSubject);    }

    @Override
    public DeleteResponse deleteSubject(Long id) {
        return null;
    }
}
