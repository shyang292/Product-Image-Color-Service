package ca.mec.productimage.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

  @JsonProperty("product_code")
  @NotBlank(message = "product code is mandatory")
  private String code;

  @NotBlank(message = "name is mandatory")
  private String name;

  @NotBlank(message = "image url is mandatory")
  private String imageUrl;

  @SuppressWarnings("unchecked")
  @JsonProperty("default_image_urls")
  private void unpackNested(Map<String,String> default_image_urls) {
    this.imageUrl = default_image_urls.get("main_image_url");
  }

  private List<String> colors;

}
