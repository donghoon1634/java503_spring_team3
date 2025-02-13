package bitc.fullstack503.java503_team3.mapper;

import bitc.fullstack503.java503_team3.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProductMapper {

  //상품 목록 조회
  List<ProductDTO> selectProductList() throws Exception;
  //카테고리별 상품 목록 조회
  List<ProductDTO> selectProductListByCategory(int categoryNum);
  // 가격대별 상품 목록 조회
  List<ProductDTO> selectProductListByPrice(Map<String, Object> priceRange);
  // 지역별 상품 목록 조회
  List<ProductDTO> selectProductListByLocalGu(int localGuNum);
  //상품 등록
  void insertProduct(ProductDTO product) throws Exception;
  //상품 상세 조회
  ProductDTO selectProductDetail(int productNum) throws Exception;
  //상품 삭제
  void deleteProduct(int productNum) throws Exception;
  //상품 수정
  void updateProduct(ProductDTO product) throws Exception;

}
