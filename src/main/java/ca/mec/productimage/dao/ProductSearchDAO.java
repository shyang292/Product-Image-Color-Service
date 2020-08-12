package ca.mec.productimage.dao;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductSearchDAO {

  public String getProducts(String keyword) throws IOException {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        "https://www.mec.ca/api/v1/products/search?keywords="+keyword, String.class);
    return responseEntity.getBody();
    }
  }
