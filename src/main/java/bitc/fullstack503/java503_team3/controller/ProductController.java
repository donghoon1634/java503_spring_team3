package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.CategoryDTO;
import bitc.fullstack503.java503_team3.dto.ProductDTO;
import bitc.fullstack503.java503_team3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

  @Autowired
  private ProductService productService;

  // 상품 목록 페이지 (일반적으로 렌더링)
  @GetMapping("/potato/trade")
  public String showProductList(Model model) {
    List<ProductDTO> productList = productService.getAllProducts(); // 전체 상품 목록 가져오기
    List<CategoryDTO> categoryList = productService.getAllCategories();   // 카테고리 목록 조회
    List<String> localGuList = productService.getAllLocalGu(); // 지역구 목록 가져오기
    model.addAttribute("productList", productList); // 타임리프로 전달될 상품 목록
    model.addAttribute("categoryList", categoryList);  // 카테고리 목록
    model.addAttribute("localGuList", localGuList);  // 타임리프로 전달될 지역구 목록
    return "product/productList";
  }
  //지역 가져오기
  @GetMapping("/potato/trade/local-gus")
  @ResponseBody
  public List<String> getLocalGuNames() {
    return productService.getAllLocalGu();
  }

  // 지역에 따라 필터링된 상품 목록을 AJAX로 반환
  @GetMapping("/potato/trade/products/local")
  @ResponseBody
  public List<ProductDTO> fetchProductsByLocal(@RequestParam("localGuName") String localGuName) {
    List<ProductDTO> products = productService.getProductsByLocal(localGuName);
    if (products == null) {
      return new ArrayList<>();  // 빈 배열 반환
    }
    return products;
  }

  // 카테고리 목록 가져오기
  @GetMapping("/potato/trade/categories")
  @ResponseBody
  public List<CategoryDTO> getCategories() {
    return productService.getAllCategories();  // 카테고리 목록 반환
  }

  // 카테고리별 상품 목록 조회
  @GetMapping("/potato/trade/products/category")
  @ResponseBody
  public List<ProductDTO> fetchProductsByCategory(@RequestParam("categoryName") String categoryName) {
    List<ProductDTO> products = productService.getProductsByCategoryName(categoryName);
    if (products == null) {
      return new ArrayList<>();  // 빈 배열 반환
    }
    return products;
  }
  //나눔
  @GetMapping("/potato/trade/products/share")
  @ResponseBody
  public List<ProductDTO> fetchShareProducts() {
    // 'share' 상태인 상품 목록을 반환하는 서비스 메서드 호출
    return productService.getShareProducts();
  }
  // 전체 상품 목록을 반환하는 메서드(초기화 버튼)
  @GetMapping("/potato/trade/products")
  @ResponseBody
  public List<ProductDTO> getAllProducts() {
    return productService.getAllProducts();  // 전체 상품 목록을 반환
  }


  // 상품 정보 상세 페이지로 가져오기
  @GetMapping("/potato/trade/productDetail")
  public String productDetail(@RequestParam("productNum") int productNum, Model model) {

    ProductDTO product = productService.getProductDetail(productNum);
    model.addAttribute("product", product);
    return "product/productDetail";

  }

}
