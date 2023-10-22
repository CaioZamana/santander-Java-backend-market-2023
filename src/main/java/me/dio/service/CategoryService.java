package me.dio.service;

import me.dio.dto.CategoryDTO;
import me.dio.model.Category;
import me.dio.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * O CategoryService é uma classe de serviço que lida com a lógica de negócio relacionada às categorias no sistema de atendimento ao mercado.
 */
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Cria uma nova categoria com base nas informações fornecidas no CategoryDTO
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO.getName()); // Cria uma nova instância de Category
        return categoryRepository.save(category); // Salva a categoria no banco de dados e a retorna
    }

    // Obtém uma categoria pelo seu ID
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + categoryId));
    }

    // Obtém todas as categorias existentes
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Atualiza os dados de uma categoria com base no ID e nas informações fornecidas no CategoryDTO
    public Category updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category category = getCategoryById(categoryId); // Obtém a categoria existente pelo ID
        category.setName(categoryDTO.getName()); // Atualiza o nome da categoria com o novo nome fornecido
        return categoryRepository.save(category); // Salva as alterações no banco de dados e retorna a categoria atualizada
    }

    // Deleta uma categoria com base no seu ID
    public void deleteCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            categoryRepository.deleteById(categoryId); // Deleta a categoria do banco de dados
        }
        // Caso a categoria não exista, não faz nada (não lança exceção)
    }

    // Outros métodos relacionados à lógica de negócio das categorias podem ser implementados aqui
}
