package me.dio.dto;

/**
 * Classe que representa o objeto de transferência de dados (DTO) para um produto.
 * É usada para receber e enviar dados relacionados a um produto entre a camada de controle (controller)
 * e a camada de serviço (service) da aplicação.
 *
 * "id" Identificador único do produto (opcional).
 * "name" Nome do produto.
 * "unit" Unidade do produto (ex: kg, unidade, litro).
 * "price" Preço do produto.
 * "categoryId" Identificador único da categoria do produto (opcional).
 */
public class ProductDTO {
    private Long id;
    private String name;
    private String unit;
    private Double price;
    private Long categoryId;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String unit, Double price, Long categoryId) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
