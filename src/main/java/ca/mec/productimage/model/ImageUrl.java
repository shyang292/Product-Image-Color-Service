package ca.mec.productimage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageUrl {
  @JsonProperty("main_image_url")
  @NotBlank(message = "image url is mandatory")
  private String imageUrl;
}
