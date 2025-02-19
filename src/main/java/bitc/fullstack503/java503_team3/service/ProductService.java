package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.CategoryDTO;
import bitc.fullstack503.java503_team3.dto.ProductDTO;
import java.util.List;

public interface ProductService {

  // 전체 상품 목록 가져오기
  List<ProductDTO> getAllProducts();

  // 지역구 목록 가져오기
  List<String> getAllLocalGu();

  // 지역에 따른 상품 목록 가져오기
  List<ProductDTO> getProductsByLocal(String localGuName);

  // 카테고리 목록 조회
  List<CategoryDTO> getAllCategories();

  // 카테고리명으로 상품 목록 조회
  List<ProductDTO> getProductsByCategoryName(String categoryName);

  // "나눔" 상품 목록 가져오기
  List<ProductDTO> getShareProducts();


  // 상품 정보 tradeBoardIdx로 가져오기
    ProductDTO getProductByTradeBoardIdx(int tradeBoardIdx);
}
