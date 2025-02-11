package bitc.fullstack503.java503_team3.mapper;

import bitc.fullstack503.java503_team3.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper {
//    목록
    public List<BoardDTO> selectBoardList() ;
//  조회수 증가
    public void updateHitCnt(int idx) ;

//    게시물 작성
    void insertBoard(BoardDTO board);

    BoardDTO selectBoardDetail(int idx);


// 상세
}
