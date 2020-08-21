package ca.mec.productimage.service;

import ca.mec.productimage.dto.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

public interface ProductImageColourService {
   List<ProductDTO> getProductImageColour(String keyword) throws JsonProcessingException;
}
