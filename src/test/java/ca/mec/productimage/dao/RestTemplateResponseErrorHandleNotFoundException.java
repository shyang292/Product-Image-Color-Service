package ca.mec.productimage.dao;


import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import ca.mec.productimage.exception.NotFoundException;
import ca.mec.productimage.exception.RestTemplateResponseErrorHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {NotFoundException.class})
@RestClientTest
public class RestTemplateResponseErrorHandleNotFoundException {


  @Autowired
  private MockRestServiceServer server;
  @Autowired private RestTemplateBuilder builder;
  @Test
  public void givenRemoteApiCall_whenNotFound_thenThrowNotFoundException() {
    Assertions.assertNotNull(this.builder);
    Assertions.assertNotNull(this.server);

    RestTemplate restTemplate = this.builder
        .errorHandler(new RestTemplateResponseErrorHandler())
        .build();

    this.server
        .expect(ExpectedCount.once(), requestTo(
            "https://mec.imgix.net/medias/sys_master/fallback/fallback/9066744676382/6005445-ASG01-fallback.jpg?palette=json"
        ))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withStatus(HttpStatus.NOT_FOUND));

    Assertions.assertThrows(NotFoundException.class, ()->{
      restTemplate.getForEntity(
          "https://mec.imgix.net/medias/sys_master/fallback/fallback/9066744676382/6005445-ASG01-fallback.jpg?palette=json"
          , String.class);
    });
    this.server.verify();
  }
}
