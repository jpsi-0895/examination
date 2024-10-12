package com.shubhamjajoo.examsite.controller.admin;

import com.shubhamjajoo.examsite.entity.Exam;
import com.shubhamjajoo.examsite.payload.response.DeleteResponse;
import com.shubhamjajoo.examsite.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/exam")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class ExamController {
    
    @Autowired
    private ExamService examService;

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        return ResponseEntity.ok(examService.createExam(exam));
    }

    @GetMapping("{id}")
    public ResponseEntity<Exam> readExam(@PathVariable("id") Long id) {
        return ResponseEntity.ok(examService.readExam(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Exam> updateExam(
            @RequestBody Exam exam,
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(examService.updateExam(exam, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponse> deleteExam(@PathVariable("id") Long id) {
        DeleteResponse response = examService.deleteExam(id);
        return ResponseEntity.ok(response);
    }
}
