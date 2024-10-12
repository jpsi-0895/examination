package com.shubhamjajoo.examsite.service.impl;

import com.shubhamjajoo.examsite.entity.Question;
import com.shubhamjajoo.examsite.entity.Option;
import com.shubhamjajoo.examsite.exception.ResourceNotFoundException;
import com.shubhamjajoo.examsite.payload.response.DeleteResponse;
import com.shubhamjajoo.examsite.repository.OptionRepository;
import com.shubhamjajoo.examsite.repository.QuestionRepository;
import com.shubhamjajoo.examsite.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public Question createQuestion(Question question) {
        Question savedQuestion = questionRepository.save(question);
        List<Option> optionList = question.getOptions().stream().map(option -> {
            Option newOption = new Option();
            newOption.setQuestion(savedQuestion);
            newOption.setOption(option.getOption());
            optionRepository.save(newOption);
            return newOption;
        }).toList();
        savedQuestion.setOptions(optionList);
        return savedQuestion;
    }

    @Override
    public Question readQuestion(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question", "id"));
    }

    @Override
    public Question updateQuestion(Question question, Long id) {
        Question oldQuestion = questionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Question", "id"));

        oldQuestion.setId(id);
        oldQuestion.setQuestion(question.getQuestion());
        oldQuestion.setAnswer(question.getAnswer());
        oldQuestion.setHandle(question.getHandle());
        oldQuestion.setQuestionType(question.getQuestionType());
        oldQuestion.setCategory(question.getCategory());
        oldQuestion.setSubject(question.getSubject());

        if(!question.getOptions().isEmpty()) {
            List<Option> optionList = question.getOptions().stream().map(option -> {
                Option oldOption = optionRepository.findById(option.getId()).orElseThrow(
                        () -> new ResourceNotFoundException("Option", "id"));
                oldOption.setId(option.getId());
                oldOption.setQuestion(question);
                oldOption.setOption(option.getOption());

                optionRepository.save(oldOption);
                return oldOption;
            }).toList();

            oldQuestion.setOptions(optionList);
        }
        return questionRepository.save(oldQuestion);

    }

    @Override
    public DeleteResponse deleteQuestion(Long id) {
        return null;
    }
}
