package ca.mec.productimage.dto;

import ca.mec.productimage.model.Color;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class ProductDTO {


  @JsonProperty("product_code")
  private String code;

  private String name;

  private String imageUrl;

  @SuppressWarnings("unchecked")
  @JsonProperty("default_image_urls")
  private void unpackNested(Map<String,String> default_image_urls) {
    this.imageUrl = default_image_urls.get("main_image_url");
  }

  private List<Color> colors;

}
