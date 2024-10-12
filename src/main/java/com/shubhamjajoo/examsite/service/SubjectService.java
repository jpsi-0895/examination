package com.shubhamjajoo.examsite.service;

import com.shubhamjajoo.examsite.entity.Subject;
import com.shubhamjajoo.examsite.payload.response.DeleteResponse;

public interface SubjectService {
    public Subject createSubject(Subject subject);

    public Subject readSubject(Long id);

    public Subject updateSubject(Subject subject, Long id);

    public DeleteResponse deleteSubject(Long id);
}
