package bitc.fullstack503.java503_team3.mapper;

import bitc.fullstack503.java503_team3.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    // 작성
    void insertBoard(BoardDTO board);
    // 목록
    List<BoardDTO> selectBoardList();
    // 조회수 증가
    void updateHitCint(int idx);
    // 상세보기
    BoardDTO selectBoardDetail(int idx) ;
}
