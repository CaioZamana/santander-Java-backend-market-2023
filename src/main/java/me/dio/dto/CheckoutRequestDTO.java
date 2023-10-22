package me.dio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import me.dio.model.PaymentMethod;


/**
 * Classe que representa o objeto de transferência de dados (DTO) para uma solicitação de checkout.
 * É usada para receber e enviar dados relacionados a uma solicitação de checkout entre a camada de controle (controller)
 * e a camada de serviço (service) da aplicação.
 *
 * "total" Valor total da compra.
 * "paymentMethod" Método de pagamento escolhido para a compra.
 */
public class CheckoutRequestDTO {
    @Min(value = 0, message = "O total não pode ser negativo")
    private Double total;

    @NotNull(message = "O método de pagamento não pode ser nulo")
    private PaymentMethod paymentMethod;

    public CheckoutRequestDTO() {
    }

    public CheckoutRequestDTO(Double total, PaymentMethod paymentMethod) {
        this.total = total;
        this.paymentMethod = paymentMethod;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
