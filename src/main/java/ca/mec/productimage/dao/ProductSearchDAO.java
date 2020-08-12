package ca.mec.productimage.dao;

import ca.mec.productimage.exception.RestTemplateResponseErrorHandler;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductSearchDAO {

  @Autowired
  private RestTemplateBuilder restTemplateBuilder;

  public String getProducts(String keyword){
    RestTemplate restTemplate = restTemplateBuilder
        .errorHandler(new RestTemplateResponseErrorHandler())
        .build();
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        "https://www.mec.ca/api/v10/products/search?keywords="+keyword, String.class);
    return responseEntity.getBody();
    }
  }
