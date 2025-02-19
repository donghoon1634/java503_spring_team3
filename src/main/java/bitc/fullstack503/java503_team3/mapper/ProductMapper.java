package bitc.fullstack503.java503_team3.mapper;

import bitc.fullstack503.java503_team3.dto.CategoryDTO;
import bitc.fullstack503.java503_team3.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

  // 전체 상품 목록 조회
  List<ProductDTO> selectAllProducts();

  // 지역구 목록 가져오기
  List<String> getAllLocalGu();

  // 지역에 따른 상품 목록 조회
  List<ProductDTO> getProductsByLocal(@Param("localGuName") String localGuName);

  // 카테고리 목록 가져오기
  List<CategoryDTO> getAllCategories();

  // 카테고리명 기준으로 상품 목록 조회
  List<ProductDTO> getProductsByCategoryName(@Param("categoryName") String categoryName);

  // 나눔 상품 목록 조회
  List<ProductDTO> getShareProducts();

  // 특정 상품의 상세 정보를 조회
  ProductDTO getProductDetail(int productNum);

  //DB에 상품 등록하기
  void insertProduct(ProductDTO productDTO); // 상품 등록 메서드
}
