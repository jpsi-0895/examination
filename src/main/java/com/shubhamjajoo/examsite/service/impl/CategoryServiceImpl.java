package com.shubhamjajoo.examsite.service.impl;

import com.shubhamjajoo.examsite.entity.Category;
import com.shubhamjajoo.examsite.exception.ResourceNotFoundException;
import com.shubhamjajoo.examsite.payload.response.DeleteResponse;
import com.shubhamjajoo.examsite.repository.CategoryRepository;
import com.shubhamjajoo.examsite.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
            return categoryRepository.save(category);
    }

    @Override
    public Category readCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        Category oldCategory = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id"));

        oldCategory.setId(id);
        oldCategory.setTitle(category.getTitle());
        oldCategory.setDescription(category.getDescription());
        oldCategory.setHandle(category.getHandle());

        return categoryRepository.save(oldCategory);
    }

    @Override
    public DeleteResponse deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id"));
        categoryRepository.delete(category);
        return new DeleteResponse("Deleted Successfully", "Category");
    }
}
