package com.practice.springjpaformock.repository;

import com.practice.springjpaformock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
