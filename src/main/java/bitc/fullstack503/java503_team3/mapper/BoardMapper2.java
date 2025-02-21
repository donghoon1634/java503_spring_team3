package bitc.fullstack503.java503_team3.mapper;
import bitc.fullstack503.java503_team3.dto.UserlifeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper2
{
  public List<UserlifeDTO> selectMyBoardList (String memberId) throws Exception;
}
