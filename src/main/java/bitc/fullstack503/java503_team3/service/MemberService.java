package bitc.fullstack503.java503_team3.service;
import bitc.fullstack503.java503_team3.dto.MemberDTO;
public interface MemberService
{
  public boolean isMemberId (String id) throws Exception;
  
  public boolean isMemberNickname (String name) throws Exception;
  
  public void signUp (MemberDTO member) throws Exception;
  
  public MemberDTO signIn (MemberDTO member) throws Exception;
}
