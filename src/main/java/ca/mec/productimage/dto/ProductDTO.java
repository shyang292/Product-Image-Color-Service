package ca.mec.productimage.dto;

import ca.mec.productimage.model.Color;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class ProductDTO {


  @JsonProperty("product_code")
  @ApiModelProperty(notes = "product code")
  private String code;

  @ApiModelProperty(notes = "product name")
  private String name;

  @ApiModelProperty(notes = "product main image url")
  private String imageUrl;

  @SuppressWarnings("unchecked")
  @JsonProperty("default_image_urls")
  private void unpackNested(Map<String,String> default_image_urls) {
    this.imageUrl = default_image_urls.get("main_image_url");
  }

  @ApiModelProperty(notes = "main colors for product image using imgix color palette API")
  private List<Color> colors;

}
