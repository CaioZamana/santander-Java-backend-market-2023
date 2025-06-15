import com.fasterxml.jackson.databind.ObjectMapper;
import me.dio.controller.CartController;
import me.dio.dto.CartItemDTO;
import me.dio.model.CartItem;
import me.dio.service.CartService;
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

@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void addToCart() throws Exception {
        CartItemDTO dto = new CartItemDTO(1L, 2, 3.0);
        CartItem saved = new CartItem(1L, 2, 3.0);
        Mockito.when(cartService.addToCart(any(CartItemDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/cart/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productId").value(1L));
    }

    @Test
    void getAllCartItems() throws Exception {
        CartItem item = new CartItem(1L, 1, 3.0);
        Mockito.when(cartService.getAllCartItems()).thenReturn(Collections.singletonList(item));

        mockMvc.perform(get("/cart/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value(1L));
    }

    @Test
    void removeFromCartSuccess() throws Exception {
        Mockito.when(cartService.removeFromCart(1L)).thenReturn(true);

        mockMvc.perform(delete("/cart/delete/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Produto removido do carrinho."));
    }

    @Test
    void removeFromCartNotFound() throws Exception {
        Mockito.when(cartService.removeFromCart(99L)).thenReturn(false);

        mockMvc.perform(delete("/cart/delete/{id}", 99L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Produto não encontrado no carrinho."));
    }

    @Test
    void getCartItem() throws Exception {
        CartItem item = new CartItem(1L, 1, 2.0);
        Mockito.when(cartService.getCartItemById(1L)).thenReturn(item);

        mockMvc.perform(get("/cart/get/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1L));
    }
}
