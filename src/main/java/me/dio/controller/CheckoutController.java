package me.dio.controller;

import jakarta.validation.Valid;
import me.dio.dto.CheckoutRequestDTO;
import me.dio.model.Checkout;
import me.dio.service.CheckoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controlador responsável pelas requisições relacionadas aos checkouts de compras.
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    // Endpoint para finalizar um checkout
    @PostMapping("/post")
    public ResponseEntity<Checkout> finalizeCheckout(@Valid @RequestBody CheckoutRequestDTO checkoutRequestDTO) {
        Checkout finalizedCheckout = checkoutService.finalizeCheckout(checkoutRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(finalizedCheckout);
    }

    // Endpoint para atualizar o método de pagamento de um checkout específico com base no checkoutId
    @PutMapping("/put/{checkoutId}")
    public ResponseEntity<Checkout> updatePaymentMethod(
            @PathVariable Long checkoutId,
            @Valid @RequestBody CheckoutRequestDTO checkoutRequestDTO
    ) {
        Checkout updatedCheckout = checkoutService.updatePaymentMethod(checkoutId, checkoutRequestDTO);
        if (updatedCheckout != null) {
            return ResponseEntity.ok(updatedCheckout);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para obter informações de um checkout específico com base no checkoutId
    @GetMapping("/get/{checkoutId}")
    public ResponseEntity<Checkout> getCheckoutById(@PathVariable Long checkoutId) {
        Checkout checkout = checkoutService.getCheckoutById(checkoutId);
        if (checkout != null) {
            return ResponseEntity.ok(checkout);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para listar todos os checkouts existentes
    @GetMapping("/get/all")
    public ResponseEntity<List<Checkout>> getAllCheckouts() {
        List<Checkout> checkouts = checkoutService.getAllCheckouts();
        return ResponseEntity.ok(checkouts);
    }
}
