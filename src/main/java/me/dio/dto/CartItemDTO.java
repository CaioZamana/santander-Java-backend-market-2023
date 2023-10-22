package me.dio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Classe que representa o objeto de transferência de dados (DTO) para um item de carrinho.
 * É usada para receber e enviar dados relacionados a um item de carrinho entre a camada de controle (controller)
 * e a camada de serviço (service) da aplicação.
 *
 * "productId" ID do produto que será adicionado ao carrinho.
 * "quantity" Quantidade do produto que será adicionada ao carrinho (deve ser maior ou igual a 1).
 * "salePrice" Preço de venda do produto que será utilizado no carrinho (não pode ser nulo).
 */
public class CartItemDTO {
    @NotNull(message = "O ID do produto não pode ser nulo")
    private Long productId;

    @Min(value = 1, message = "A quantidade mínima deve ser 1")
    private Integer quantity;

    @NotNull(message = "O preço de venda não pode ser nulo")
    private Double salePrice;

    public CartItemDTO() {
    }

    public CartItemDTO(Long productId, Integer quantity, Double salePrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.salePrice = salePrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }
}
