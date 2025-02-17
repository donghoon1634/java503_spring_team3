package bitc.fullstack503.java503_team3.service;
import bitc.fullstack503.java503_team3.dto.MemberDTO;
import bitc.fullstack503.java503_team3.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MemberServiceImpl implements MemberService
{
  @Autowired
  private MemberMapper memberMapper;
  
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
  public boolean signIn (MemberDTO member) throws Exception
  {
    return memberMapper.signIn (member) > 0;
  }
  
  @Override
  public MemberDTO memberInfo (String memberId) throws Exception
  {
    return memberMapper.memberInfo (memberId);
  }
}
