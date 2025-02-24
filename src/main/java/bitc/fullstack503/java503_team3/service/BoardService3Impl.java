package bitc.fullstack503.java503_team3.service;
import bitc.fullstack503.java503_team3.mapper.BoardMapper3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardService3Impl implements BoardService3
{
  @Autowired
  private BoardMapper3 boardMapper3;
  
  @Override
  public void deleteUserlifeCommentMember (String memberId) throws Exception
  {
    boardMapper3.deleteUserlifeCommentMember (memberId);
  }
  
  @Override
  public void deleteUserlifeFileMember (String memberId) throws Exception
  {
    List<Integer> ulIdx = boardMapper3.selectUlUdxFromUserlifeWhereMemberId (memberId);
    if (ulIdx == null || ulIdx.isEmpty ())
    {
      return;
    }
    boardMapper3.deleteUserlifeFileMember (ulIdx);
  }
  
  @Override
  public void deleteUserlifeMember (String memberId) throws Exception
  {
    boardMapper3.deleteUserlifeMember (memberId);
  }
}
