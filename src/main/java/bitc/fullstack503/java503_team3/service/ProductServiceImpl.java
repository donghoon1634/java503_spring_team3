package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.CategoryDTO;
import bitc.fullstack503.java503_team3.dto.ProductDTO;
import bitc.fullstack503.java503_team3.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductMapper productMapper;

  // 전체 상품 목록을 반환하는 메서드
  @Override
  public List<ProductDTO> getAllProducts() {
    return productMapper.selectAllProducts();
  }

  // 지역구 목록 호출
  @Override
  public List<String> getAllLocalGu() {
    return productMapper.getAllLocalGu();
  }

  // 지역에 맞는 상품 목록을 반환하는 메서드
  @Override
  public List<ProductDTO> getProductsByLocal(String localGuName) {
    return productMapper.getProductsByLocal(localGuName);
  }

  // 카테고리 목록 조회
  @Override
  public List<CategoryDTO> getAllCategories() {
    return productMapper.getAllCategories();
  }

  // 카테고리명으로 상품 목록 조회
  @Override
  public List<ProductDTO> getProductsByCategoryName(String categoryName) {
    return productMapper.getProductsByCategoryName(categoryName);
  }

  // "나눔" 상품 목록을 반환하는 메서드
  @Override
  public List<ProductDTO> getShareProducts() {
    return productMapper.getShareProducts();  // '나눔' 상품을 가져오는 메서드 호출
  }

  // 특정 상품의 세부 정보 조회
  @Override
  public ProductDTO getProductDetail(int productNum) {
    return productMapper.getProductDetail(productNum); // 제품 상세 정보를 가져오기 위한 num
  }

  @Override
  public void addProduct(ProductDTO productDTO) {
    // DB에 상품 추가
    productMapper.insertProduct(productDTO);
  }
}
