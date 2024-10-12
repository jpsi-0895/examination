package com.shubhamjajoo.examsite.controller.admin;

import com.shubhamjajoo.examsite.entity.Subject;
import com.shubhamjajoo.examsite.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/subject")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject exam) {
        return ResponseEntity.ok(subjectService.createSubject(exam));
    }

    @GetMapping("{id}")
    public ResponseEntity<Subject> readSubject(@PathVariable("id") Long id) {
        return ResponseEntity.ok(subjectService.readSubject(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Subject> updateSubject(
            @RequestBody Subject subject,
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(subjectService.updateSubject(subject, id));
    }
}
