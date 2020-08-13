package ca.mec.productimage.controller;

import ca.mec.productimage.dao.ImageColorDAO;
import ca.mec.productimage.dto.ProductDTO;
import ca.mec.productimage.service.ProductImageColourService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product-image-color")
@Api(value = "Product Image Color Service", description = "a web service for determinging the main colors for product images from MEC catalog")
public class ProductImageColorController {

  @Autowired
  private ProductImageColourService productImageColourService;

  @Autowired
  private ImageColorDAO imageColourDAO;

  @GetMapping("/{keyword}")
  @ApiOperation(value = "return the product code, name and image uri for the first 5 products along with colours as hex codes", response = ProductDTO.class)
  public List<ProductDTO> getProductImageColors(@PathVariable String keyword) throws IOException {
    return productImageColourService.getProductImageColour(keyword);
  }

}
