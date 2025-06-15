import com.fasterxml.jackson.databind.ObjectMapper;
import me.dio.controller.CategoryController;
import me.dio.dto.CategoryDTO;
import me.dio.model.Category;
import me.dio.service.CategoryService;
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

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void createCategory() throws Exception {
        CategoryDTO dto = new CategoryDTO("Fruits");
        Category saved = new Category("Fruits");
        Mockito.when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/categories/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Fruits"));
    }

    @Test
    void getCategory() throws Exception {
        Category category = new Category("Vegetables");
        Mockito.when(categoryService.getCategoryById(1L)).thenReturn(category);

        mockMvc.perform(get("/categories/get/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Vegetables"));
    }

    @Test
    void getAllCategories() throws Exception {
        Category category = new Category("Beverages");
        Mockito.when(categoryService.getAllCategories()).thenReturn(Collections.singletonList(category));

        mockMvc.perform(get("/categories/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Beverages"));
    }

    @Test
    void updateCategory() throws Exception {
        CategoryDTO dto = new CategoryDTO("New");
        Category updated = new Category("New");
        Mockito.when(categoryService.updateCategory(eq(1L), any(CategoryDTO.class))).thenReturn(updated);

        mockMvc.perform(put("/categories/put/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New"));
    }

    @Test
    void deleteCategory() throws Exception {
        mockMvc.perform(delete("/categories/delete/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Categoria excluída com sucesso."));
    }
}
