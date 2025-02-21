package bitc.fullstack503.java503_team3.service;
import bitc.fullstack503.java503_team3.dto.UserlifeDTO;
import bitc.fullstack503.java503_team3.mapper.BoardMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardService2Impl implements BoardService2
{
  @Autowired
  private BoardMapper2 BoardMapper2;
  
  @Override
  public List<UserlifeDTO> selectMyBoardList (String memberId) throws Exception
  {
    return BoardMapper2.selectMyBoardList (memberId);
  }
}
