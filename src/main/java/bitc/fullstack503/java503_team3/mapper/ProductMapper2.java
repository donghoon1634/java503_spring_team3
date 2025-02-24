package bitc.fullstack503.java503_team3.mapper;
import bitc.fullstack503.java503_team3.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductMapper2
{
  public List<ProductDTO> getMyProductList (String memberId) throws Exception;
  
  public void deleteProduct (int productNum) throws Exception;
  
  public void deleteProductMember (String memberId) throws Exception;
}
