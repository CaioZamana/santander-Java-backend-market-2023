package me.dio.controller;

import jakarta.validation.Valid;
import me.dio.dto.CartItemDTO;
import me.dio.model.CartItem;
import me.dio.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pelas requisições relacionadas ao carrinho de compras.
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Endpoint para adicionar um item ao carrinho
    @PostMapping("/post")
    public ResponseEntity<CartItem> addToCart(@Valid @RequestBody CartItemDTO cartItemDTO) {
        CartItem addedCartItem = cartService.addToCart(cartItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCartItem);
    }

    // Endpoint para remover um item do carrinho com base no productId
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long productId) {
        boolean removed = cartService.removeFromCart(productId);
        if (removed) {
            return ResponseEntity.ok("Produto removido do carrinho.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado no carrinho.");
        }
    }

    // Endpoint para obter informações de um item específico no carrinho com base no productId
    @GetMapping("/get/{productId}")
    public ResponseEntity<CartItem> getCartItem(@PathVariable Long productId) {
        CartItem cartItem = cartService.getCartItemById(productId);
        return ResponseEntity.ok(cartItem);
    }

    // Endpoint para listar todos os itens presentes no carrinho
    @GetMapping("/get/all")
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }
}
