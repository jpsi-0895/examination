package com.shubhamjajoo.examsite.repository;

import com.shubhamjajoo.examsite.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
