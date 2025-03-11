package com.practice.springjpaformock.repository;

import com.practice.springjpaformock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
