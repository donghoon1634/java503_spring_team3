package bitc.fullstack503.java503_team3.dto;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ProductCommentDTO
{
  private int productCommentIdx;
  private int productCommentProductIdx;
  private String productCommentContent;
  private String productCommentMemberId;
  private LocalDateTime productCommentCreated;
  private String productCommentMemberNickname;
}
