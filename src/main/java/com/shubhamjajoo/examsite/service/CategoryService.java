package com.shubhamjajoo.examsite.service;

import com.shubhamjajoo.examsite.entity.Category;
import com.shubhamjajoo.examsite.payload.response.DeleteResponse;

public interface CategoryService {
    public Category createCategory(Category category);

    public Category readCategory(Long id);

    public Category updateCategory(Category category, Long id);

    public DeleteResponse deleteCategory(Long id);
}
