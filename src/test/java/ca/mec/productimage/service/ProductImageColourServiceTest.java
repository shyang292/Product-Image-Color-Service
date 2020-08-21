package ca.mec.productimage.service;


import static org.mockito.ArgumentMatchers.anyString;

import ca.mec.productimage.dao.ImageColorDAO;
import ca.mec.productimage.dao.ProductSearchDAO;
import ca.mec.productimage.dto.ProductDTO;
import ca.mec.productimage.model.Color;
import ca.mec.productimage.model.ColorPaletteResult;
import ca.mec.productimage.model.ProductSearchResult;
import ca.mec.productimage.service.impl.ProductImageColourServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {"mec.product.image.color.service.result.size=5", "mec.cdn.name=\"cdn.mec.ca\"","mec.imgix.name=\"mec.imgix.net\""})
class ProductImageColourServiceTest {

  @InjectMocks
  private ProductImageColourServiceImpl productImageColourService;

  @Mock
  private ProductSearchDAO productSearchDAO;

  @Mock
  private ImageColorDAO imageColorDAO;

  @Mock
  private ObjectMapper objectMapper;
  @Mock
  private ObjectReader objectReader;

  @Value("${mec.product.image.color.service.result.size}")
  private int size;

  @Test
  void getProductImageColour() throws IOException {
    String keyword = "bike";
    String productResult = "{products : "
        + "["
        + "{product_code: \"6005-445\", name: \"Kanzo A Bicycle 2020\", default_image_urls :{main_image_url: \"https://cdn.mec.ca/medias/sys_master/fallback/fallback/9066744676382/6005445-ASG01-fallback.jpg\"}}, "
        + "{product_code: \"6006-020\", name: \"2020 Habit 6 Bicycle\", default_image_urls :{main_image_url: \"https://cdn.mec.ca/medias/sys_master/fallback/fallback/9058549202974/6006020-BK000-fallback.jpg\"}}, "
        + "{product_code: \"6006-062\", name: \"2020 Fat CAAD 1 Bicycle\", default_image_urls :{main_image_url: \"https://cdn.mec.ca/medias/sys_master/fallback/fallback/9052109209630/6006062-GYY00-fallback.jpg\"}}, "
        + "{product_code: \"6005-990\", name: \"2020 Synapse Tiagra Bicycle\", default_image_urls :{main_image_url: \"https://cdn.mec.ca/medias/sys_master/fallback/fallback/9055023792158/6005990-MDN00-fallback.jpg\"}}, "
        + "{product_code: \"6006-017\", name: \"2020 Habit 3 Bicycle\", default_image_urls :{main_image_url: \"https://cdn.mec.ca/medias/sys_master/fallback/fallback/9067250974750/6006017-UVT00-fallback.jpg\"}}, "
        + "{product_code: \"6006-105\", name: \"2020 Quick Neo 2 SL Remixte E-Bicycle\", default_image_urls :{main_image_url: \"https://cdn.mec.ca/medias/sys_master/fallback/fallback/9067251761182/6006105-TRQ00-fallback.jpg\"}}"
        + "]}";
    String colorResult = "{colors:[{hex: \"#0d0d11\"}, {hex: \"#383739\"}, {hex: \"#534539\"}, "
        + "{hex: \"#808183\"}, {hex: \"#a7a7a8\"}, {hex: \"#c39474\"} ]}";
    Mockito.lenient().when(productSearchDAO.getProducts(anyString())).thenReturn(productResult);
    Mockito.lenient().when(imageColorDAO.getColors(anyString())).thenReturn(colorResult);
    ProductSearchResult productSearchResult = new ProductSearchResult();
    List<ProductDTO> list = Arrays.asList(
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
                new Color(), new Color(), new Color())),
        new ProductDTO("6006-105", "2020 Quick Neo 2 SL Remixte E-Bicycle",
            "https://cdn.mec.ca/medias/sys_master/fallback/fallback/9067251761182/6006105-TRQ00-fallback.jpg",
            Arrays.asList(new Color(), new Color(), new Color(),
                new Color(), new Color(), new Color()))
        );
    productSearchResult.setProducts(list);
    ColorPaletteResult colorPaletteResult = new ColorPaletteResult();
    colorPaletteResult.setColors(Arrays.asList(new Color(), new Color(), new Color(),
        new Color(), new Color(), new Color()));
    Assertions.assertNotNull(objectMapper);
    Mockito.lenient().when(objectMapper.readerFor(any(Class.class))).thenReturn(objectReader);
    Mockito.lenient().when(objectReader.readValue(anyString())).thenReturn(
        productSearchResult, colorPaletteResult
    );

    ReflectionTestUtils.setField(productImageColourService, "size", 5);
    ReflectionTestUtils.setField(productImageColourService, "mecCdnName", "cdn.mec.ca");
    ReflectionTestUtils.setField(productImageColourService, "mecImgixNet", "mec.imgix.net");
    Assertions.assertNotNull(productImageColourService);
    List<ProductDTO> resultList = productImageColourService.getProductImageColour(keyword);
    Assertions.assertNotNull(resultList);
    Assertions.assertTrue(resultList.size()>0);
    Assertions.assertNotNull(resultList.get(0));
    Assertions.assertEquals(resultList.get(0).getCode(), "6005-445");
    Assertions.assertEquals(resultList.get(0).getName(), "Kanzo A Bicycle 2020");
    Assertions.assertEquals(resultList.get(0).getImageUrl(), "https://cdn.mec.ca/medias/sys_master/fallback/fallback/9066744676382/6005445-ASG01-fallback.jpg");
    Assertions.assertTrue(resultList.get(0).getColors().size()>0);

  }

  @Test
  void getTestProperty(){
    System.out.println(size);
    Assertions.assertTrue(size == 5);
  }

}