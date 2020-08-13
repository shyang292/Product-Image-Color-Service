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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
class ImageColorDAOTest {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private ImageColorDAO imageColorDAO;

  @Value("${imgix.color.pallette.parameter}")
  private String palletteParameter;

  @Test
  void getColors() throws JSONException {
    String res = "{colors:[{hex: \"#0d0d11\"}, {hex: \"#383739\"}, {hex: \"#534539\"}, "
        + "{hex: \"#808183\"}, {hex: \"#a7a7a8\"}, {hex: \"#c39474\"} ]}";

    Mockito.when(restTemplate.getForEntity(anyString(), any()))
        .thenReturn(new ResponseEntity(res, HttpStatus.OK));

    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        "https://mec.imgix.net/medias/sys_master/fallback/fallback/9066744676382/6005445-ASG01-fallback.jpg?"+palletteParameter, String.class);
    Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    JSONAssert.assertEquals(res, responseEntity.getBody(), JSONCompareMode.LENIENT);
  }
}