package ca.mec.productimage.dao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {"MEC.product.search.url=https://www.mec.ca/api/v1/products/search?keywords="})
class ProductSearchDAOTest {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private ProductSearchDAO productSearchDAO;

  @Value("${MEC.product.search.url}")
  private String productSearchUrl;

  @Test
  void getProducts() throws JSONException {
    String res = "{products : "
        + "["
        + "{product_code: \"6005-445\", name: \"Kanzo A Bicycle 2020\", default_image_urls :{main_image_url: \"https://cdn.mec.ca/medias/sys_master/fallback/fallback/9066744676382/6005445-ASG01-fallback.jpg\"}}, "
        + "{product_code: \"6006-020\", name: \"2020 Habit 6 Bicycle\", default_image_urls :{main_image_url: \"https://cdn.mec.ca/medias/sys_master/fallback/fallback/9058549202974/6006020-BK000-fallback.jpg\"}}]"
        + "}";

    Mockito.when(restTemplate.getForEntity(anyString(), any()))
        .thenReturn(new ResponseEntity(res, HttpStatus.OK));

    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        productSearchUrl+"bike", String.class);
    Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    JSONAssert.assertEquals(res, responseEntity.getBody(), JSONCompareMode.LENIENT);
  }

  @Test
  void testOverrideProperty(){
    System.out.println(productSearchUrl);
  }
}