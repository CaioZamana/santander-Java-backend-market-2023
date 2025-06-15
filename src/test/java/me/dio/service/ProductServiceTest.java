package me.dio.service;

import me.dio.dto.ProductDTO;
import me.dio.model.Category;
import me.dio.model.Product;
import me.dio.repository.CategoryRepository;
import me.dio.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProductWithCategory() {
        ProductDTO dto = new ProductDTO(null, "Apple", "kg", 3.0, 1L);
        Category category = new Category("Fruits");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Product saved = new Product("Apple", "kg", 3.0, category);
        when(productRepository.save(any(Product.class))).thenReturn(saved);

        Product result = productService.createProduct(dto);

        assertEquals("Apple", result.getName());
        assertSame(category, result.getCategory());
        verify(categoryRepository).findById(1L);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void createProductWithoutCategory() {
        ProductDTO dto = new ProductDTO(null, "Banana", "kg", 2.0, null);
        Product saved = new Product("Banana", "kg", 2.0, null);
        when(productRepository.save(any(Product.class))).thenReturn(saved);

        Product result = productService.createProduct(dto);

        assertEquals("Banana", result.getName());
        assertNull(result.getCategory());
        verify(productRepository).save(any(Product.class));
        verifyNoInteractions(categoryRepository);
    }

    @Test
    void updateProduct() {
        Category category = new Category("Fruits");
        Product existing = new Product("Old", "u", 1.0, null);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProductDTO dto = new ProductDTO(null, "New", "box", 5.0, 2L);
        Product result = productService.updateProduct(1L, dto);

        assertEquals("New", result.getName());
        assertEquals("box", result.getUnit());
        assertEquals(5.0, result.getPrice());
        assertSame(category, result.getCategory());
        verify(productRepository).save(existing);
    }
}
