package bitc.fullstack503.java503_team3.service;
import bitc.fullstack503.java503_team3.dto.MemberContentDTO;
import bitc.fullstack503.java503_team3.dto.MemberDTO;
import bitc.fullstack503.java503_team3.dto.MemberProfileDTO;

import java.util.List;
public interface MemberService
{
  public boolean isMemberId (String id) throws Exception;
  
  public boolean isMemberNickname (String name) throws Exception;
  
  public void signUp (MemberDTO member) throws Exception;
  
  public void signUpInsert (MemberDTO member) throws Exception;
  
  public boolean signIn (MemberDTO member) throws Exception;
  
  public MemberDTO memberInfo (String memberId) throws Exception;
  
  public void memberProfile (List<MemberProfileDTO> memberProfile) throws Exception;
  
  public String memberProfileHref (String memberId) throws Exception;
  
  public String getMemberContent (String memberId) throws Exception;
  
  public void setMemberContent (MemberContentDTO memberContentDTO) throws Exception;
  
  public void memberUpdate (MemberDTO member) throws Exception;
}
