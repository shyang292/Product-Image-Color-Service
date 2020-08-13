package ca.mec.productimage.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

  public static final Contact DEFAULT_CONTACT = new Contact(
      "Shao", "", "shyangcan@gmail.com");

  public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
      "Product Image Colors Service", "a web service for determining the main colours for product images from the MEC catalog", "1.0",
      "", DEFAULT_CONTACT,
      "", "", Arrays.asList());

  private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
      new HashSet<String>(Arrays.asList("application/json",
          "application/xml"));

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(DEFAULT_API_INFO)
        .produces(DEFAULT_PRODUCES_AND_CONSUMES)
        .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
  }
}