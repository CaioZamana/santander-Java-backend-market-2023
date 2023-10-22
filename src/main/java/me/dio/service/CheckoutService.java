package me.dio.service;

import me.dio.dto.CheckoutRequestDTO;
import me.dio.model.CartItem;
import me.dio.model.Checkout;
import me.dio.repository.CheckoutRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

/**
 * O CheckoutService é uma classe de serviço que lida com a lógica de negócio relacionada ao processo de checkout no sistema de atendimento ao mercado.
 */
@Service
public class CheckoutService {
    private final CartService cartService;
    private final CheckoutRepository checkoutRepository;

    public CheckoutService(CartService cartService, CheckoutRepository checkoutRepository) {
        this.cartService = cartService;
        this.checkoutRepository = checkoutRepository;
    }

    // Finaliza o checkout com base nas informações fornecidas no CheckoutRequestDTO
    public Checkout finalizeCheckout(CheckoutRequestDTO checkoutRequestDTO) {
        // Obter todos os itens do carrinho
        List<CartItem> cartItems = cartService.getAllCartItems();

        // Calcular o total de vendas somando o preço de cada produto no carrinho
        double totalSales = cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getSalePrice() * cartItem.getQuantity())
                .sum();

        // Arredonda o total para duas casas decimais
        double totalRounded = roundToTwoDecimalPlaces(totalSales);

        // Cria uma nova instância de Checkout com o total e o método de pagamento fornecidos
        Checkout checkout = new Checkout();
        checkout.setTotal(totalRounded);
        checkout.setPaymentMethod(checkoutRequestDTO.getPaymentMethod());

        return checkoutRepository.save(checkout); // Salva o checkout no banco de dados e o retorna
    }

    // Atualiza o método de pagamento de um checkout com base no seu ID e nas informações fornecidas no CheckoutRequestDTO
    public Checkout updatePaymentMethod(Long checkoutId, CheckoutRequestDTO checkoutRequestDTO) {
        Optional<Checkout> existingCheckout = checkoutRepository.findById(checkoutId);
        if (existingCheckout.isPresent()) {
            Checkout checkout = existingCheckout.get();
            checkout.setPaymentMethod(checkoutRequestDTO.getPaymentMethod()); // Atualiza o método de pagamento
            return checkoutRepository.save(checkout); // Salva as alterações no banco de dados e retorna o checkout atualizado
        }
        return null;
    }

    // Obtém um checkout pelo seu ID, retorna null se não for encontrado
    public Checkout getCheckoutById(Long checkoutId) {
        return checkoutRepository.findById(checkoutId).orElse(null);
    }

    // Método auxiliar para arredondar o valor para duas casas decimais
    public double roundToTwoDecimalPlaces(double value) {
        BigDecimal decimalValue = BigDecimal.valueOf(value);
        BigDecimal roundedValue = decimalValue.setScale(2, RoundingMode.HALF_EVEN);
        return roundedValue.doubleValue();
    }

    // Obtém todos os checkouts existentes
    public List<Checkout> getAllCheckouts() {
        return checkoutRepository.findAll();
    }
}
