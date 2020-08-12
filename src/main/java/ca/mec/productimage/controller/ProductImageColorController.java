package ca.mec.productimage.controller;

import ca.mec.productimage.dao.ImageColorDAO;
import ca.mec.productimage.dto.ProductDTO;
import ca.mec.productimage.service.ProductImageColourService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product-image-color")
public class ProductImageColorController {

  @Autowired
  private ProductImageColourService productImageColourService;

  @Autowired
  private ImageColorDAO imageColourDAO;

  @GetMapping("/{keyword}")
  public List<ProductDTO> testDAO(@PathVariable String keyword) throws IOException {
    return productImageColourService.getProductImageColour(keyword);
  }

}
