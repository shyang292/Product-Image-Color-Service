package ca.mec.productimage.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Color {

  @NotBlank(message = "hex color is mandatory")
  @ApiModelProperty(notes = "main color as hex codes")
  private String hex;

}
