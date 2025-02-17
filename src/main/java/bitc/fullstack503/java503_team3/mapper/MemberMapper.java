package bitc.fullstack503.java503_team3.mapper;
import bitc.fullstack503.java503_team3.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface MemberMapper
{
  public int isMemberId (String memberId) throws Exception;
  
  public int isMemberNickname (String memberNickname) throws Exception;
  
  public void signUp (MemberDTO member) throws Exception;
  
  public int signIn (MemberDTO member) throws Exception;
  
  public MemberDTO memberInfo (String memberId) throws Exception;
}
