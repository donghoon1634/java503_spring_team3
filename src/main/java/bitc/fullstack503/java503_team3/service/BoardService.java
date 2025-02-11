package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.BoardDTO;

import java.util.List;

public interface BoardService {
//    게시물목록
    public List<BoardDTO> selectBoardList();
//게시물 작성
    void insertBoard(BoardDTO board);



//게시물 상세
BoardDTO selectBoardDetail(int idx);
}
