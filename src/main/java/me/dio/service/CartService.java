package me.dio.service;

import me.dio.dto.CartItemDTO;
import me.dio.model.CartItem;
import me.dio.model.Product;
import me.dio.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * O CartService é uma classe de serviço que lida com a lógica de negócio relacionada ao carrinho de compras no sistema de atendimento ao mercado.
 */
@Service
public class CartService {

    private final CartRepository cartItemRepository;
    private final ProductService productService;

    @Autowired
    public CartService(CartRepository cartItemRepository, ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }

    // Adiciona um item ao carrinho com base no CartItemDTO recebido
    public CartItem addToCart(CartItemDTO cartItemDTO) {
        // Verifica se já existe um item de carrinho com o mesmo productId
        CartItem existingCartItem = cartItemRepository.findByProductId(cartItemDTO.getProductId());

        // Obtém as informações do produto com base no productId
        Product product = productService.getProductById(cartItemDTO.getProductId());

        // Define o preço de venda do item com base no preço do produto
        double salePrice = product.getPrice();

        // Se já existir um item de carrinho com o mesmo productId, atualiza a quantidade e o preço de venda
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + (cartItemDTO.getQuantity() != null ? cartItemDTO.getQuantity() : 1));
            existingCartItem.setSalePrice(salePrice);
            cartItemRepository.save(existingCartItem); // Salva o item de carrinho atualizado no banco de dados
            return existingCartItem; // Retorna o item de carrinho atualizado
        } else {
            // Se não existir um item de carrinho com o mesmo productId, cria um novo item de carrinho
            CartItem cartItem = new CartItem(
                    cartItemDTO.getProductId(),
                    cartItemDTO.getQuantity() != null ? cartItemDTO.getQuantity() : 1,
                    salePrice
            );
            cartItemRepository.save(cartItem); // Salva o novo item de carrinho no banco de dados
            return cartItem; // Retorna o novo item de carrinho
        }
    }

    // Remove um item do carrinho com base no productId fornecido
    public boolean removeFromCart(Long productId) {
        // Verifica se existe um item de carrinho com o productId fornecido
        CartItem existingItem = cartItemRepository.findById(productId).orElse(null);
        if (existingItem != null) {
            cartItemRepository.delete(existingItem); // Remove o item de carrinho do banco de dados
            return true;
        }
        return false;
    }

    // Obtém um item de carrinho com base no productId fornecido
    public CartItem getCartItemById(Long productId) {
        return cartItemRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("CartItem not found with productId: " + productId));
    }

    // Obtém todos os itens de carrinho existentes
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    // Outros métodos relacionados à lógica de negócio do carrinho de compras podem ser implementados aqui
}
