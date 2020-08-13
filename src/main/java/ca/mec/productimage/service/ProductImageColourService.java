package ca.mec.productimage.service;


import ca.mec.productimage.dao.ImageColorDAO;
import ca.mec.productimage.dao.ProductSearchDAO;
import ca.mec.productimage.dto.ProductDTO;
import ca.mec.productimage.model.ColorPaletteResult;
import ca.mec.productimage.model.ProductSearchResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProductImageColourService {

  @Autowired
  private ProductSearchDAO productSearchDAO;

  @Autowired
  private ImageColorDAO imageColorDAO;

  @Value("${mec.cdn.name}")
  private String mecCdnName;

  @Value("${mec.imgix.name}")
  private String mecImgixNet;

  public List<ProductDTO> getProductImageColour(String keyword) throws IOException {
    String productSearchString = productSearchDAO.getProducts(keyword);
    ProductSearchResult searchProducts = new ObjectMapper()
        .readerFor(ProductSearchResult.class)
        .readValue(productSearchString);
    List<ProductDTO> list = searchProducts.getProducts();
    List<ProductDTO> resultList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      //1. replace cdn.mec.ca with mec.imgix.net
      ProductDTO productDTO = list.get(i);
      String imageUrl = productDTO.getImageUrl();
      imageUrl = imageUrl.replaceAll(mecCdnName, mecImgixNet);
      //2. add palette=json parameter
      String imageReult = imageColorDAO.getColors(imageUrl);
      ColorPaletteResult colorPaletteResult = new ObjectMapper()
        .readerFor(ColorPaletteResult.class)
        .readValue(imageReult);
      productDTO.setColors(colorPaletteResult.getColors());
      resultList.add(productDTO);
    }
    return resultList;
  }
}
