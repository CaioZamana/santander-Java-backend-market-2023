package me.dio.service;


import me.dio.dto.ProductDTO;
import me.dio.model.Category;
import me.dio.model.Product;
import me.dio.repository.CategoryRepository;
import me.dio.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * O ProductService é uma classe de serviço que lida com a lógica de negócio relacionada aos produtos no sistema de atendimento ao mercado.
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Cria um novo produto com base nas informações fornecidas no ProductDTO
    public Product createProduct(ProductDTO productDTO) {
        // Recupera a categoria pelo seu ID do banco de dados, se estiver presente no ProductDTO
        Category category = null;
        if (productDTO.getCategoryId() != null) {
            category = getCategoryById(productDTO.getCategoryId());
        }

        // Cria uma nova instância de Product com as informações do ProductDTO e a categoria associada
        Product product = new Product(
                productDTO.getName(),
                productDTO.getUnit(),
                productDTO.getPrice(),
                category // Define a categoria associada ao produto
        );

        return productRepository.save(product); // Salva o produto no banco de dados e o retorna
    }

    // Atualiza um produto com base no seu ID e nas informações fornecidas no ProductDTO
    public Product updateProduct(Long productId, ProductDTO productDTO) {
        Product product = getProductById(productId); // Obtém o produto existente pelo seu ID
        product.setName(productDTO.getName()); // Atualiza o nome do produto
        product.setUnit(productDTO.getUnit()); // Atualiza a unidade do produto
        product.setPrice(productDTO.getPrice()); // Atualiza o preço do produto

        // Se houver um categoryId no ProductDTO, recupere a categoria correspondente pelo seu ID e associe-a ao produto
        if (productDTO.getCategoryId() != null) {
            Category category = getCategoryById(productDTO.getCategoryId());
            product.setCategory(category);
        }

        return productRepository.save(product); // Salva as alterações no banco de dados e retorna o produto atualizado
    }

    // Deleta um produto com base no seu ID
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId); // Deleta o produto do banco de dados
    }

    // Obtém um produto pelo seu ID
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + productId));
    }

    // Método auxiliar para recuperar a categoria pelo seu ID
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + categoryId));
    }

    // Obtém todos os produtos existentes
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Outros métodos relacionados à lógica de negócio dos produtos podem ser implementados aqui
}