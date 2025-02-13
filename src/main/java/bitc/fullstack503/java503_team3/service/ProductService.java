package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.ProductDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Map;

public interface ProductService {
  //  상품 목록 조회
  List<ProductDTO> selectProductList() throws Exception;

  // 카테고리별 상품 목록 조회
  List<ProductDTO> selectProductListByCategory(int categoryNum) throws Exception;

  // 가격대별 상품 목록 조회
  List<ProductDTO> selectProductListByPrice(Map<String, Object> priceRange) throws Exception;

  // 지역별 상품 목록 조회
  List<ProductDTO> selectProductListByLocalGu(int localGuNum) throws Exception;

  //  상품 등록
  void insertProduct(ProductDTO product, MultipartHttpServletRequest multipart) throws Exception;

  //  상품 상세조회
  ProductDTO selectProductDetail(int productNum) throws Exception;

  //  상품 삭제
  void deleteProduct(int productNum) throws Exception;

  //  상품 수정
  void updateProduct(ProductDTO product) throws Exception;
}