package bitc.fullstack503.java503_team3.dto;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MemberDTO
{
  private int memberIdx;
  private String memberId;
  private String memberPw;
  private String memberNickname;
  private String memberPhone;
  private String memberAddr;
  private String memberAddrDetail;
  private LocalDateTime memberCreated;
  private LocalDateTime memberUpdated;
}
