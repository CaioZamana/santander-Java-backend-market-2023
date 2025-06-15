import com.fasterxml.jackson.databind.ObjectMapper;
import me.dio.controller.CheckoutController;
import me.dio.dto.CheckoutRequestDTO;
import me.dio.model.Checkout;
import me.dio.model.PaymentMethod;
import me.dio.service.CheckoutService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CheckoutController.class)
class CheckoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckoutService checkoutService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void finalizeCheckout() throws Exception {
        CheckoutRequestDTO dto = new CheckoutRequestDTO(10.0, PaymentMethod.PIX);
        Checkout saved = new Checkout(10.0, PaymentMethod.PIX);
        Mockito.when(checkoutService.finalizeCheckout(any(CheckoutRequestDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/checkout/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.total").value(10.0));
    }

    @Test
    void updatePaymentMethodNotFound() throws Exception {
        CheckoutRequestDTO dto = new CheckoutRequestDTO(10.0, PaymentMethod.PIX);
        Mockito.when(checkoutService.updatePaymentMethod(eq(99L), any(CheckoutRequestDTO.class))).thenReturn(null);

        mockMvc.perform(put("/checkout/put/{id}", 99L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCheckoutById() throws Exception {
        Checkout checkout = new Checkout(20.0, PaymentMethod.DINHEIRO);
        Mockito.when(checkoutService.getCheckoutById(1L)).thenReturn(checkout);

        mockMvc.perform(get("/checkout/get/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(20.0));
    }

    @Test
    void getAllCheckouts() throws Exception {
        Checkout checkout = new Checkout(30.0, PaymentMethod.CARTAO_CREDITO);
        Mockito.when(checkoutService.getAllCheckouts()).thenReturn(Collections.singletonList(checkout));

        mockMvc.perform(get("/checkout/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].total").value(30.0));
    }
}
