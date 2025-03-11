package com.practice.springjpaformock.service;

import com.practice.springjpaformock.model.Category;
import com.practice.springjpaformock.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    // 1 - категория не создается, из-за того, что название занято
    @Test
    void create_shouldThrowException_whenCategoryAlreadyExists() {
        Category existingCategory = new Category();
        existingCategory.setId(1);
        existingCategory.setName("Наушники");

        Mockito.when(categoryRepository.findByName(Mockito.anyString()))
                .thenReturn(Optional.of(existingCategory));

        Category category = new Category();
        category.setName("Наушники");

//        Executable executable = new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                categoryService.create(category);
//            }
//        };

        Executable executable = () -> categoryService.create(category);

        RuntimeException ex = assertThrows(RuntimeException.class, executable);

        assertEquals("Категория с таким названием уже существует", ex.getMessage());
    }

    // 2 - категория создается
    @Test
    void create_shouldReturnCreated_whenCategoryNameIsNotTaken() {
        Mockito.when(categoryRepository.findByName(Mockito.anyString()))
                .thenReturn(Optional.empty());

        Category category = new Category();
        category.setName("Мебель");

        Category savedCategory = categoryService.create(category);
        assertEquals(category.getName(), savedCategory.getName());
    }
}
