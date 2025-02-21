package bitc.fullstack503.java503_team3.controller;
import bitc.fullstack503.java503_team3.dto.CategoryDTO;
import bitc.fullstack503.java503_team3.dto.MemberDTO;
import bitc.fullstack503.java503_team3.dto.ProductDTO;
import bitc.fullstack503.java503_team3.service.MemberService;
import bitc.fullstack503.java503_team3.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Controller
public class ProductController
{
  @Autowired
  private ProductService productService;
  @Autowired
  private MemberService memberService;
  
  // 상품 목록 페이지 (일반적으로 렌더링)
  @GetMapping ("/potato/trade")
  public String showProductList (Model model, HttpServletRequest request)
  {
    HttpSession session = request.getSession (false);  // 이미 세션이 있다면 가져오기
    if (session != null)
    {
      MemberDTO memberInfo = (MemberDTO) session.getAttribute ("memberInfo");  // 로그인된 사용자 정보 가져오기
      if (memberInfo != null)
      {
        model.addAttribute ("isLoggedIn", true);  // 로그인한 상태로 표시
      }
      else
      {
        model.addAttribute ("isLoggedIn", false);  // 로그인하지 않은 상태
      }
    }
    else
    {
      model.addAttribute ("isLoggedIn", false);  // 세션이 없으면 로그인하지 않은 상태
    }
    List<ProductDTO> productList = productService.getAllProducts (); // 전체 상품 목록 가져오기
    List<CategoryDTO> categoryList = productService.getAllCategories ();   // 카테고리 목록 조회
    List<String> localGuList = productService.getAllLocalGu (); // 지역구 목록 가져오기
    model.addAttribute ("productList", productList); // 타임리프로 전달될 상품 목록
    model.addAttribute ("categoryList", categoryList);  // 카테고리 목록
    model.addAttribute ("localGuList", localGuList);  // 타임리프로 전달될 지역구 목록
    return "product/productList";
  }
  
  // 지역 가져오기
  @GetMapping ("/potato/trade/local-gus")
  @ResponseBody
  public List<String> getLocalGuNames ()
  {
    return productService.getAllLocalGu ();
  }
  
  // 지역에 따라 필터링된 상품 목록을 AJAX로 반환
  @GetMapping ("/potato/trade/products/local")
  @ResponseBody
  public List<ProductDTO> fetchProductsByLocal (@RequestParam ("localGuName") String localGuName)
  {
    List<ProductDTO> products = productService.getProductsByLocal (localGuName);
    if (products == null)
    {
      return new ArrayList<> ();  // 빈 배열 반환
    }
    return products;
  }
  
  // 카테고리 목록 가져오기
  @GetMapping ("/potato/trade/categories")
  @ResponseBody
  public List<CategoryDTO> getCategories ()
  {
    return productService.getAllCategories ();  // 카테고리 목록 반환
  }
  
  // 카테고리별 상품 목록 조회
  @GetMapping ("/potato/trade/products/category")
  @ResponseBody
  public List<ProductDTO> fetchProductsByCategory (@RequestParam ("categoryName") String categoryName)
  {
    List<ProductDTO> products = productService.getProductsByCategoryName (categoryName);
    if (products == null)
    {
      return new ArrayList<> ();  // 빈 배열 반환
    }
    return products;
  }
  
  // 나눔
  @GetMapping ("/potato/trade/products/share")
  @ResponseBody
  public List<ProductDTO> fetchShareProducts ()
  {
    // 'share' 상태인 상품 목록을 반환하는 서비스 메서드 호출
    return productService.getShareProducts ();
  }
  
  // 전체 상품 목록을 반환하는 메서드(초기화 버튼)
  @GetMapping ("/potato/trade/products")
  @ResponseBody
  public List<ProductDTO> getAllProducts ()
  {
    return productService.getAllProducts ();  // 전체 상품 목록을 반환
  }
  
  // 상품 정보 상세 페이지로 가져오기
  @GetMapping ("/potato/trade/productDetail")
  public String productDetail (@RequestParam ("productNum") int productNum, Model model) throws Exception
  {
    ProductDTO product = productService.getProductDetail (productNum);
    String memberName = product.getMemberIdx();
    String memberProfile = null;
    if (memberName == null)
    {
      memberProfile = product.getProductImg();
      memberName = product.getProductName ();
      model.addAttribute ("memberProfile", memberProfile);
      model.addAttribute ("memberName", memberName);
      model.addAttribute ("product", product);
    }
    else
    {
      memberProfile = memberService.memberProfileHref (product.getMemberIdx ());
      MemberDTO memberInfo = memberService.memberInfo (product.getMemberIdx ());
      memberName = memberInfo.getMemberNickname ();
      model.addAttribute ("product", product);
      model.addAttribute ("memberProfile", memberProfile);
      model.addAttribute ("memberName", memberName);
    }
    return "product/productDetail";
  }
  
  // 로그인시 제품등록 버튼이 보이고 누르면 페이지로 가는 메서드
  @GetMapping ("/potato/trade/productWrite")
  public String productWritePage (HttpServletRequest request)
  {
    // 세션에서 로그인 여부 확인
    HttpSession session = request.getSession (false);
    if (session != null && session.getAttribute ("memberInfo") != null)
    {
      // 로그인한 사용자라면 제품 등록 페이지로 이동
      return "product/productWrite";  // productWrite.html로 이동
    }
    else
    {
      // 로그인 안 된 사용자라면 로그인 페이지로 리디렉션
      return "redirect:/member";  // 로그인 페이지로 리디렉션
    }
  }
  
  // 상품 등록 처리
  @PostMapping ("/potato/trade/productWrite")
  public String productWrite (@RequestParam ("productName") String productName,
                              @RequestParam ("productInfo") String productInfo,
                              @RequestParam ("productPrice") String productPrice,
                              @RequestParam ("localGuName") String localGuName,
                              @RequestParam ("file") MultipartFile file,
                              HttpServletRequest request,  // 세션을 확인하기 위한 HttpServletRequest 추가
                              RedirectAttributes redirectAttributes)
  {
    // 세션에서 로그인된 회원의 idx 정보 가져오기
    HttpSession session = request.getSession (false);
    if (session == null || session.getAttribute ("memberInfo") == null)
    {
      redirectAttributes.addFlashAttribute ("errMsg", "로그인이 필요합니다.");
      return "redirect:/member";  // 로그인 페이지로 리디렉션
    }
    // 세션에서 MemberDTO 객체 가져오기
    MemberDTO memberInfo = (MemberDTO) session.getAttribute ("memberInfo");
    //지역구 유효성 검사
    List<String> validLocalGuList = Arrays.asList("부산진구", "해운대구", "사하구", "남구", "금정구", "사상구",
            "수영구", "강서구", "동구", "서구", "중구", "영도구", "북구",
            "연제구", "동래구", "기장군");

    if (!validLocalGuList.contains(localGuName.trim())) {
      redirectAttributes.addFlashAttribute("errMsg", "지역구를 정확히 입력해주세요.");
      return "redirect:/potato/trade/productWrite";  // 지역구 오류 시 다시 입력 폼으로
    }

    // memberInfo가 null이 아니라면, memberIdx 값을 가져오기
    if (memberInfo != null)
    {
      String memberIdx = memberInfo.getMemberId ();  // MemberDTO에서 memberIdx 가져오기
      if (!file.isEmpty ())
      {
        try
        {
          // 이미지 파일 저장 경로 설정
          String uploadDir = "C:/fullstack503/spring/upload/trade/";  // 실제 경로로 수정 필요
          String fileName = file.getOriginalFilename ();
          Path filePath = Paths.get (uploadDir + fileName);
          // 파일 저장
          Files.write (filePath, file.getBytes ());
          // 이미지 URL 생성
          String imageUrl = "/resources/" + fileName;  // 정적 리소스 경로에 맞게 수정
          //          String imageUrl = "/upload/dir/" + fileName;  // 웹 경로에 맞게 수정
          // productPrice를 String에서 int로 변환 (나눔도 처리)
          String price = productPrice.equals ("나눔") ? "나눔" : String.valueOf(Integer.parseInt(productPrice));
          // ProductDTO 에 데이터 설정
          ProductDTO productDTO = new ProductDTO ();
          productDTO.setProductName (productName);
          productDTO.setProductInfo (productInfo);
          productDTO.setProductPrice(price);
          productDTO.setLocalGuName (localGuName);
          productDTO.setProductImg (imageUrl);
          productDTO.setMemberIdx (memberIdx);  // 로그인된 회원의 idx 설정
          // 상품 등록 서비스 호출
          productService.addProduct (productDTO);
          redirectAttributes.addFlashAttribute ("message", "상품이 성공적으로 등록되었습니다.");
          return "redirect:/potato/trade";  // 상품 리스트 페이지로 리디렉션
        }
        catch (IOException e)
        {
          redirectAttributes.addFlashAttribute ("errMsg", "파일 업로드 중 오류가 발생했습니다.");
          return "redirect:/potato/trade/productWrite";  // 다시 등록 페이지로 리디렉션
        }
      }
      else
      {
        redirectAttributes.addFlashAttribute ("errMsg", "이미지가 첨부되지 않았습니다.");
        return "redirect:/potato/trade/productWrite";  // 이미지 첨부 안됨 오류 메시지
      }
    }
    else
    {
      redirectAttributes.addFlashAttribute ("errMsg", "로그인이 필요합니다.");
      return "redirect:/member";  // 로그인 페이지로 리디렉션
    }
  }

  // 검색 기능
  @GetMapping("/potato/trade/products/search")
  public String searchProducts(@RequestParam("searchTerm") String searchTerm,
                               @RequestParam(value = "categoryName", required = false) String categoryName,
                               @RequestParam(value = "localGuName", required = false) String localGuName,
                               Model model) {
    // 검색어가 비어있으면 빈 리스트 반환
    if (searchTerm == null || searchTerm.trim().isEmpty()) {
      model.addAttribute("productList", new ArrayList<>()); // 빈 리스트 전달
      return "productList"; // 검색어 없을 경우 제품 목록 페이지로 리턴
    }

    // 필터링된 상품 목록을 모델에 추가
    List<ProductDTO> productList = productService.searchProducts(searchTerm, categoryName, localGuName);
    model.addAttribute("productList", productList);

    // 검색 결과를 제품 목록 페이지에 전달
    return "product/productList"; // 해당 페이지에서 결과 출력
  }


}
