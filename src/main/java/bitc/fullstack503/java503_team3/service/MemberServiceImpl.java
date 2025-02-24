package bitc.fullstack503.java503_team3.service;
import bitc.fullstack503.java503_team3.dto.MemberContentDTO;
import bitc.fullstack503.java503_team3.dto.MemberDTO;
import bitc.fullstack503.java503_team3.dto.MemberProfileDTO;
import bitc.fullstack503.java503_team3.mapper.MemberContentMapper;
import bitc.fullstack503.java503_team3.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberServiceImpl implements MemberService
{
  @Autowired
  private MemberMapper memberMapper;
  @Autowired
  private MemberContentMapper memberContentMapper;
  
  @Override
  public boolean isMemberId (String id) throws Exception
  {
    int isId = memberMapper.isMemberId (id);
    return isId > 0;
  }
  
  @Override
  public boolean isMemberNickname (String name) throws Exception
  {
    int isNickname = memberMapper.isMemberNickname (name);
    return isNickname > 0;
  }
  
  @Override
  public void signUp (MemberDTO member) throws Exception
  {
    memberMapper.signUp (member);
  }
  
  @Override
  public void signUpInsert (MemberDTO member) throws Exception
  {
    memberMapper.signUpInsert (member);
  }
  
  @Override
  public boolean signIn (MemberDTO member) throws Exception
  {
    return memberMapper.signIn (member) > 0;
  }
  
  @Override
  public MemberDTO memberInfo (String memberId) throws Exception
  {
    return memberMapper.memberInfo (memberId);
  }
  
  @Override
  public void memberProfile (List<MemberProfileDTO> memberProfile) throws Exception
  {
    memberMapper.memberProfile (memberProfile);
  }
  
  @Override
  public String memberProfileHref (String memberId) throws Exception
  {
    return memberMapper.memberProfileHref (memberId);
  }
  
  @Override
  public String getMemberContent (String memberId) throws Exception
  {
    return memberContentMapper.getMemberContent (memberId);
  }
  
  @Override
  public void setMemberContent (MemberContentDTO memberContentDTO) throws Exception
  {
    memberContentMapper.setMemberContent (memberContentDTO);
  }
  
  @Override
  public void memberUpdate (MemberDTO member) throws Exception
  {
    memberMapper.memberUpdate (member);
  }
  
  @Override
  public void deleteMemberProfileMember (String memberId) throws Exception
  {
    memberMapper.deleteMemberProfileMember (memberId);
  }
  
  @Override
  public void deleteMemberContentMember (String memberId) throws Exception
  {
    memberContentMapper.deleteMemberContentMember (memberId);
  }
  
  @Override
  public void deleteMember (String memberId) throws Exception
  {
    memberMapper.deleteMember (memberId);
  }
}
