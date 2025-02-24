package bitc.fullstack503.java503_team3.mapper;
import bitc.fullstack503.java503_team3.dto.ProductCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductCommentMapper
{
  public void insertProductComment (ProductCommentDTO productCommentDTO) throws Exception;
  
  public List<ProductCommentDTO> getProductComment (int productCommentProductIdx) throws Exception;
  
  public void deleteProductComment (int productCommentProductIdx) throws Exception;
}
