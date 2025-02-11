package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    // 작성
    void insertBoard(BoardDTO board);
    // 목록
    List<BoardDTO> selectBoardList();
    // 상세
    BoardDTO selectBoardDetail(int idx);
}
