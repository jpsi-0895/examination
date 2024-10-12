package com.shubhamjajoo.examsite.service;

import com.shubhamjajoo.examsite.entity.Question;
import com.shubhamjajoo.examsite.payload.response.DeleteResponse;

public interface QuestionService  {
    public Question createQuestion(Question question);

    public Question readQuestion(Long id);

    public Question updateQuestion(Question question, Long id);

    public DeleteResponse deleteQuestion(Long id);
}
