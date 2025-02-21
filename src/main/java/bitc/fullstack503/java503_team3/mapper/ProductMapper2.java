package bitc.fullstack503.java503_team3.mapper;
import bitc.fullstack503.java503_team3.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductMapper2
{
  List<ProductDTO> getMyProductList (String memberId) throws Exception;
}
