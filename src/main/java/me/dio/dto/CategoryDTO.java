package me.dio.dto;

/**
 * Classe que representa o objeto de transferência de dados (DTO) para uma categoria.
 * É usada para receber e enviar dados relacionados a uma categoria entre a camada de controle (controller)
 * e a camada de serviço (service) da aplicação.
 *
 * "name" Nome da categoria.
 */
public class CategoryDTO {
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}