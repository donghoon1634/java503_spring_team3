package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.ProductDTO;
import bitc.fullstack503.java503_team3.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/potato")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/list")
  public ModelAndView openProductList() throws Exception {
    ModelAndView mv = new ModelAndView("product/productList");
    List<ProductDTO> productList = productService.selectProductList();
    mv.addObject("productList", productList);
    return mv;
  }

  // 카테고리별 상품 목록 조회
  @GetMapping("/product/category")
  public ModelAndView getProductListByCategory(@RequestParam int categoryNum) throws Exception {
    ModelAndView mv = new ModelAndView("product/productListByCategory");
    List<ProductDTO> productList = productService.selectProductListByCategory(categoryNum);
    mv.addObject("productList", productList);
    return mv;
  }

  // 가격대별 상품 목록 조회
  @GetMapping("/product/price")
  public ModelAndView getProductListByPrice(@RequestParam Map<String, Object> priceRange) throws Exception {
    ModelAndView mv = new ModelAndView("product/productListByPrice");
    List<ProductDTO> productList = productService.selectProductListByPrice(priceRange);
    mv.addObject("productList", productList);
    return mv;
  }

  //  지역별 상품 목록 조회
  @GetMapping("/product/local")
  public ModelAndView getProductListByLocal(@RequestParam int localGuNum) throws Exception {
    ModelAndView mv = new ModelAndView("product/productListByLocal");
    List<ProductDTO> productList = productService.selectProductListByLocalGu(localGuNum);
    mv.addObject("productList", productList);
    return mv;
  }

  // 상품 등록 페이지
  @GetMapping("/write")
  public String productWrite() {
    return "product/productWrite";
  }

  // 상품 등록 처리
  @PostMapping("/insert")
  public String insertProduct(ProductDTO product, MultipartHttpServletRequest multipart) throws Exception {
    productService.insertProduct(product, multipart);
    return "redirect:/potato/list";
  }

  // 상품 상세 보기
  @GetMapping("/detail")
  public ModelAndView productDetail(@RequestParam("productNum") int productNum) throws Exception {
    ModelAndView mv = new ModelAndView("product/productDetail");
    ProductDTO product = productService.selectProductDetail(productNum);
    mv.addObject("product", product);
    return mv;
  }

  // 상품 삭제
  @PostMapping("/delete")
  public String deleteProduct(@RequestParam("productNum") int productNum) throws Exception {
    productService.deleteProduct(productNum);
    return "redirect:/potato/list";
  }

  // 상품 수정 처리
  @PostMapping("/update")
  public String updateProduct(ProductDTO product) throws Exception {
    productService.updateProduct(product);
    return "redirect:/potato/list";
  }

}
