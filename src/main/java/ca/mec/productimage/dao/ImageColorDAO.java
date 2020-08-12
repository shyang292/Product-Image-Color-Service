package ca.mec.productimage.dao;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class ImageColorDAO {

  public String getColors(String url) throws IOException {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        url+"?palette=json", String.class);
    return responseEntity.getBody();
  }

}
