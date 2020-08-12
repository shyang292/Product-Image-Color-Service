package ca.mec.productimage.model;

import ca.mec.productimage.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductSearchResult {
  private List<ProductDTO> products;
}
