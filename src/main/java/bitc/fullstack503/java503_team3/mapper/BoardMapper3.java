package bitc.fullstack503.java503_team3.mapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper3
{
  public void deleteUserlifeCommentMember (String memberId) throws Exception;
  
  public List<Integer> selectUlUdxFromUserlifeWhereMemberId (String memberId) throws Exception;
  
  public void deleteUserlifeFileMember (List<Integer> ulIdx) throws Exception;
  
  public void deleteUserlifeMember (String memberId) throws Exception;
}
