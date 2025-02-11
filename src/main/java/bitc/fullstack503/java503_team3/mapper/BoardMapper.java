package bitc.fullstack503.java503_team3.mapper;

import bitc.fullstack503.java503_team3.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    void insertBoard(BoardDTO board);
}
