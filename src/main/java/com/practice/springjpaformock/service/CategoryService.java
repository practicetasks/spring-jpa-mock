package com.practice.springjpaformock.service;

import com.practice.springjpaformock.model.Category;
import com.practice.springjpaformock.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository; // mock

    public Category create(Category category) {
        if (category.getName() == null || category.getName().isBlank()) {
            throw new RuntimeException("Название категории не может быть пустым");
        }

        Optional<Category> optional = categoryRepository.findByName(category.getName()); // Optional.empty
        if (optional.isPresent()) {
            throw new RuntimeException("Категория с таким названием уже существует");
        }

        categoryRepository.save(category);
        return category;
    }
}
