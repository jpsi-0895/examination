package com.shubhamjajoo.examsite.controller.admin;

import com.shubhamjajoo.examsite.entity.Question;
import com.shubhamjajoo.examsite.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/question")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class QuestionController {
    
    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.createQuestion(question));
    }

    @GetMapping("{id}")
    public ResponseEntity<Question> readQuestion(@PathVariable("id") Long id) {
        return ResponseEntity.ok(questionService.readQuestion(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Question> updateQuestion(
            @RequestBody Question question,
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(questionService.updateQuestion(question, id));
    }
}
