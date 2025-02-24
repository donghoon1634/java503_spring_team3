package bitc.fullstack503.java503_team3.service;
import bitc.fullstack503.java503_team3.dto.ProductDTO;
import bitc.fullstack503.java503_team3.mapper.ProductMapper;
import bitc.fullstack503.java503_team3.mapper.ProductMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService2Impl implements ProductService2
{
  @Autowired
  private ProductMapper2 productMapper2;
  
  @Override
  public List<ProductDTO> getMyProductList (String memberId) throws Exception
  {
    return productMapper2.getMyProductList (memberId);
  }
  
  @Override
  public void deleteProduct (int productNum) throws Exception
  {
    productMapper2.deleteProduct (productNum);
  }
  
  @Override
  public void deleteProductMember (String memberId) throws Exception
  {
    productMapper2.deleteProductMember (memberId);
  }
}
