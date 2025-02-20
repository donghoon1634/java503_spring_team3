package bitc.fullstack503.java503_team3.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class WebConfiguration implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // /resources/** 요청을 C:/fullstack503/spring/upload/trade/ 경로로 매핑
    registry.addResourceHandler("/resources/**")
            .addResourceLocations("file:///C:/fullstack503/spring/upload/trade/");
  }
}
