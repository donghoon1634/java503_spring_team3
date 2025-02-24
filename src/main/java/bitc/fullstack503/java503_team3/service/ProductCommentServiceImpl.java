package bitc.fullstack503.java503_team3.service;
import bitc.fullstack503.java503_team3.dto.ProductCommentDTO;
import bitc.fullstack503.java503_team3.mapper.ProductCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductCommentServiceImpl implements ProductCommentService
{
  @Autowired
  private ProductCommentMapper productCommentMapper;
  
  @Override
  public void insertProductComment (ProductCommentDTO productCommentDTO) throws Exception
  {
    productCommentMapper.insertProductComment (productCommentDTO);
  }
  
  @Override
  public List<ProductCommentDTO> getProductComment (int productCommentProductIdx) throws Exception
  {
    return productCommentMapper.getProductComment (productCommentProductIdx);
  }
  
  @Override
  public void deleteProductComment (int productCommentProductIdx) throws Exception
  {
    productCommentMapper.deleteProductComment (productCommentProductIdx);
  }
  
  @Override
  public void deleteProductCommentMember (String productCommentMemberId) throws Exception
  {
    productCommentMapper.deleteProductCommentMember (productCommentMemberId);
  }
}
