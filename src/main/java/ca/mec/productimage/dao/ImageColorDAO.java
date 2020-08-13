package ca.mec.productimage.dao;

import ca.mec.productimage.exception.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class ImageColorDAO {

  @Autowired
  private RestTemplateBuilder restTemplateBuilder;

  @Value("${imgix.color.pallette.parameter}")
  private String palletteParameter;

  public String getColors(String url){
    RestTemplate restTemplate = restTemplateBuilder
        .errorHandler(new RestTemplateResponseErrorHandler())
        .build();
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        url+"?"+palletteParameter, String.class);
    return responseEntity.getBody();
  }

}
