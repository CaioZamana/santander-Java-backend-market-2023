import com.fasterxml.jackson.databind.ObjectMapper;
import me.dio.controller.ProductController;
import me.dio.dto.ProductDTO;
import me.dio.model.Product;
import me.dio.service.ProductService;
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

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void createProduct() throws Exception {
        ProductDTO dto = new ProductDTO(null, "Apple", "kg", 3.0, null);
        Product saved = new Product("Apple", "kg", 3.0, null);
        Mockito.when(productService.createProduct(any(ProductDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/products/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Apple"));
    }

    @Test
    void getProduct() throws Exception {
        Product product = new Product("Orange", "kg", 2.5, null);
        Mockito.when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/products/get/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Orange"));
    }

    @Test
    void getAllProducts() throws Exception {
        Product product = new Product("Apple", "kg", 3.0, null);
        Mockito.when(productService.getAllProducts()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/products/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Apple"));
    }

    @Test
    void updateProduct() throws Exception {
        ProductDTO dto = new ProductDTO(null, "Grape", "kg", 4.0, null);
        Product updated = new Product("Grape", "kg", 4.0, null);
        Mockito.when(productService.updateProduct(eq(1L), any(ProductDTO.class))).thenReturn(updated);

        mockMvc.perform(put("/products/put/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Grape"));
    }

    @Test
    void deleteProduct() throws Exception {
        mockMvc.perform(delete("/products/delete/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("Produto excluído com sucesso."));
    }
}
