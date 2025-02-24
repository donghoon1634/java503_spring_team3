package bitc.fullstack503.java503_team3.mapper;
import bitc.fullstack503.java503_team3.dto.MemberContentDTO;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface MemberContentMapper
{
  public String getMemberContent (String memberId) throws Exception;
  
  public void setMemberContent (MemberContentDTO memberContentDTO) throws Exception;
  
  public void deleteMemberContentMember (String memberId) throws Exception;
}
