package com.practice.springjpaformock.service;

import com.practice.springjpaformock.model.Category;
import com.practice.springjpaformock.model.Product;
import com.practice.springjpaformock.repository.CategoryRepository;
import com.practice.springjpaformock.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product create(Product product, int categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("Категория не найдена"));

        product.setCategory(category);
        productRepository.save(product);
        return product;
    }
}
