package me.dio.service;

import me.dio.dto.CategoryDTO;
import me.dio.model.Category;
import me.dio.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteExistingCategory() {
        Category category = new Category("Food");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        categoryService.deleteCategory(1L);

        verify(categoryRepository).deleteById(1L);
    }

    @Test
    void deleteNonExistingCategory() {
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        categoryService.deleteCategory(99L);

        verify(categoryRepository, never()).deleteById(anyLong());
    }

    @Test
    void createCategory() {
        CategoryDTO dto = new CategoryDTO("New");
        Category saved = new Category("New");
        when(categoryRepository.save(any(Category.class))).thenReturn(saved);

        Category result = categoryService.createCategory(dto);

        assertEquals("New", result.getName());
        verify(categoryRepository).save(any(Category.class));
    }
}
