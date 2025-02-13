package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.ProductDTO;
import bitc.fullstack503.java503_team3.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductMapper productMapper;

  //  상품 목록 조회
  @Override
  public List<ProductDTO> selectProductList() throws Exception {
    return productMapper.selectProductList();
  }

  // 카테고리별 상품 목록 조회
  @Override
  public List<ProductDTO> selectProductListByCategory(int categoryNum) throws Exception {
    return productMapper.selectProductListByCategory(categoryNum);
  }

  //  가격별 상품 목록 조회
  @Override
  public List<ProductDTO> selectProductListByPrice(Map<String, Object> priceRange) throws Exception {
    return productMapper.selectProductListByPrice(priceRange);
  }

  // 지역별 상품 목록 조회
  @Override
  public List<ProductDTO> selectProductListByLocalGu(int localGuNum) throws Exception {
    return productMapper.selectProductListByLocalGu(localGuNum);
  }

  //  상품 등록
  @Override
  public void insertProduct(ProductDTO product, MultipartHttpServletRequest multipart) throws Exception {
    productMapper.insertProduct(product);
  }

  //  상품 상세 조회
  @Override
  public ProductDTO selectProductDetail(int productNum) throws Exception {
    return productMapper.selectProductDetail(productNum);
  }

  //  상품 삭제
  @Override
  public void deleteProduct(int productNum) throws Exception {
    productMapper.deleteProduct(productNum);
  }

  //  상품 수정
  @Override
  public void updateProduct(ProductDTO product) throws Exception {
    productMapper.updateProduct(product);
  }
}
