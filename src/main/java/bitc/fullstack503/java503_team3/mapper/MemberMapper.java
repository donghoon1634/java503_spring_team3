package bitc.fullstack503.java503_team3.mapper;
import bitc.fullstack503.java503_team3.dto.MemberDTO;
import bitc.fullstack503.java503_team3.dto.MemberProfileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MemberMapper
{
  public int isMemberId (String memberId) throws Exception;
  
  public int isMemberNickname (String memberNickname) throws Exception;
  
  public void signUp (MemberDTO member) throws Exception;
  
  public void signUpInsert (MemberDTO member) throws Exception;
  
  public int signIn (MemberDTO member) throws Exception;
  
  public MemberDTO memberInfo (String memberId) throws Exception;
  
  public void memberProfile (List<MemberProfileDTO> memberProfile) throws Exception;
  
  public String memberProfileHref (String memberId) throws Exception;
}
