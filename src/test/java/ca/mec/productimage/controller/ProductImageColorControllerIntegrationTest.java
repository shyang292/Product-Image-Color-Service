package ca.mec.productimage.controller;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ca.mec.productimage.ProductImageColourServiceApplication;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ProductImageColourServiceApplication.class)
@AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
//@AutoConfigureTestDatabase
public class ProductImageColorControllerIntegrationTest {
  @Autowired
  private MockMvc mvc;

  @Test
  public void whenValidInput_thenGetProductImageColors() throws Exception {
    mvc.perform(
        get("/api/v1/product-image-color/search?keyword=bike").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$", hasSize(5)))
        .andExpect(jsonPath("$[0].name").exists())
        .andExpect(jsonPath("$[0].imageUrl").exists())
        .andExpect(jsonPath("$[0].product_code").exists())
        .andExpect(jsonPath("$[0].colors").exists())
        .andExpect(jsonPath("$[0].colors", hasSize(6)));
  }

  @Test
  public void whenInValidInput_thenReturnInternalServerError() throws Exception {
    mvc.perform(
        get("/api/v1/product-image-color/search?keyword=1111111").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is5xxServerError())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//        .andExpect(jsonPath("$").isArray())
//        .andExpect(jsonPath("$", hasSize(5)))
//        .andExpect(jsonPath("$[0].name").exists())
//        .andExpect(jsonPath("$[0].imageUrl").exists())
//        .andExpect(jsonPath("$[0].product_code").exists())
//        .andExpect(jsonPath("$[0].colors").exists())
//        .andExpect(jsonPath("$[0].colors", hasSize(6)));


  }

}
