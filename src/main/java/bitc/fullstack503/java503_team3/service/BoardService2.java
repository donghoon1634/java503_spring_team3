package bitc.fullstack503.java503_team3.service;
import bitc.fullstack503.java503_team3.dto.UserlifeDTO;

import java.util.List;
public interface BoardService2
{
  public List<UserlifeDTO> selectMyBoardList (String memberId) throws Exception;
}
