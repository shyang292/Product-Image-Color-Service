package ca.mec.productimage.dao;

import ca.mec.productimage.exception.RestTemplateResponseErrorHandler;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductSearchDAO {

  @Autowired
  private RestTemplateBuilder restTemplateBuilder;

  @Value("${mec.product.search.url}")
  private String productSearchUrl;

  public String getProducts(String keyword){
    RestTemplate restTemplate = restTemplateBuilder
        .errorHandler(new RestTemplateResponseErrorHandler())
        .build();
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        productSearchUrl+keyword, String.class);
    return responseEntity.getBody();
    }
  }
