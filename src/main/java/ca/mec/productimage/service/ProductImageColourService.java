package ca.mec.productimage.service;


import ca.mec.productimage.dao.ImageColorDAO;
import ca.mec.productimage.dao.ProductSearchDAO;
import ca.mec.productimage.dto.ProductDTO;
import ca.mec.productimage.model.ColorPaletteResult;
import ca.mec.productimage.model.ProductSearchResult;
import com.fasterxml.jackson.core.JsonParser;
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

  @Autowired
  private ObjectMapper objectMapper;

  @Value("${mec.cdn.name}")
  private String mecCdnName;

  @Value("${mec.imgix.name}")
  private String mecImgixNet;

  @Value("${mec.product.image.color.service.result.size}")
  private int size;

  public List<ProductDTO> getProductImageColour(String keyword) throws IOException {
    String productSearchString = productSearchDAO.getProducts(keyword);
    objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    ProductSearchResult searchProducts = objectMapper
        .readerFor(ProductSearchResult.class)
        .readValue(productSearchString);
    List<ProductDTO> list = searchProducts.getProducts();
    List<ProductDTO> resultList = new ArrayList<>();
    size = Math.max(size, list.size());
    for (int i = 0; i < size; i++) {
      //1. replace cdn.mec.ca with mec.imgix.net
      ProductDTO productDTO = list.get(i);
      String imageUrl = productDTO.getImageUrl();
      imageUrl = imageUrl.replaceAll(mecCdnName, mecImgixNet);
      //2. add palette=json parameter
      String imageReult = imageColorDAO.getColors(imageUrl);
      ColorPaletteResult colorPaletteResult = objectMapper
        .readerFor(ColorPaletteResult.class)
        .readValue(imageReult);
      productDTO.setColors(colorPaletteResult.getColors());
      resultList.add(productDTO);
    }
    return resultList;
  }
}
