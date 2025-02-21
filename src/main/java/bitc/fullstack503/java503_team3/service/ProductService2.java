package bitc.fullstack503.java503_team3.service;
import bitc.fullstack503.java503_team3.dto.ProductDTO;

import java.util.List;
public interface ProductService2
{
  List<ProductDTO> getMyProductList (String memberId) throws Exception;
}
