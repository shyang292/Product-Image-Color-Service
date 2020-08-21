package ca.mec.productimage.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ca.mec.productimage.dto.ProductDTO;
import ca.mec.productimage.model.Color;
import ca.mec.productimage.service.ProductImageColourService;
import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductImageColorController.class)
class ProductImageColorControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductImageColourService productImageColourService;

  @Test
  void getProductImageColors() throws Exception {
    Mockito.when(productImageColourService.getProductImageColour("bike")).thenReturn(
        Arrays.asList(
            new ProductDTO("6005-445", "Kanzo A Bicycle 2020",
                "https://cdn.mec.ca/medias/sys_master/fallback/fallback/9066744676382/6005445-ASG01-fallback.jpg",
                Arrays.asList(new Color(), new Color(), new Color(),
                    new Color(), new Color(), new Color())),
            new ProductDTO("6006-020", "2020 Habit 6 Bicycle",
                "https://cdn.mec.ca/medias/sys_master/fallback/fallback/9058549202974/6006020-BK000-fallback.jpg",
                Arrays.asList(new Color(), new Color(), new Color(),
                    new Color(), new Color(), new Color())),
            new ProductDTO("6006-062", "2020 Fat CAAD 1 Bicycle",
                "https://cdn.mec.ca/medias/sys_master/fallback/fallback/9052109209630/6006062-GYY00-fallback.jpg",
                Arrays.asList(new Color(), new Color(), new Color(),
                    new Color(), new Color(), new Color())),
            new ProductDTO("6005-990", "2020 Synapse Tiagra Bicycle",
                "https://cdn.mec.ca/medias/sys_master/fallback/fallback/9055023792158/6005990-MDN00-fallback.jpg",
                Arrays.asList(new Color(), new Color(), new Color(),
                    new Color(), new Color(), new Color())),
            new ProductDTO("6006-017", "2020 Habit 3 Bicycle",
                "https://cdn.mec.ca/medias/sys_master/fallback/fallback/9067250974750/6006017-UVT00-fallback.jpg",
                Arrays.asList(new Color(), new Color(), new Color(),
                    new Color(), new Color(), new Color()))
        )
    );
    mockMvc.perform(get("/api/v1/product-image-color/search?keyword=bike")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$", hasSize(5)))
        //.andExpect(jsonPath("$[0]", hasItem("{name=\"Kanzo A Bicycle 2020\", imageUrl=\"https://cdn.mec.ca/medias/sys_master/fallback/fallback/9066744676382/6005445-ASG01-fallback.jpg\", colors=[{\"hex\":\"#0d0d11\"},{\"hex\":\"#383739\"},{\"hex\":\"#534539\"},{\"hex\":\"#808183\"},{\"hex\":\"#a7a7a8\"},{\"hex\":\"#c39474\"}], product_code=6005-445}")));
        .andExpect(jsonPath("$[0].name", is("Kanzo A Bicycle 2020")))
        .andExpect(jsonPath("$[0].imageUrl", is("https://cdn.mec.ca/medias/sys_master/fallback/fallback/9066744676382/6005445-ASG01-fallback.jpg")))
        .andExpect(jsonPath("$[0].product_code", is("6005-445")))
        .andExpect(jsonPath("$[0].colors", hasSize(6)));
  }
}