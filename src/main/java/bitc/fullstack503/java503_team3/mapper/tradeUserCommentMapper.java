package bitc.fullstack503.java503_team3.mapper;

import bitc.fullstack503.java503_team3.dto.userTradeCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface tradeUserCommentMapper {
    void qnaComment(userTradeCommentDTO utc);
    List<userTradeCommentDTO> getComment(int tradeBoardIdx);

}
