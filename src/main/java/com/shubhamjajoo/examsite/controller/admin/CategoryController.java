package com.shubhamjajoo.examsite.controller.admin;

import com.shubhamjajoo.examsite.entity.Category;
import com.shubhamjajoo.examsite.payload.response.DeleteResponse;
import com.shubhamjajoo.examsite.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/category")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
            return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> readCategory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.readCategory(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(
            @RequestBody Category category,
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(categoryService.updateCategory(category, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponse> deleteCategory(@PathVariable("id") Long id) {
        DeleteResponse response = categoryService.deleteCategory(id);
        return ResponseEntity.ok(response);
    }

}
