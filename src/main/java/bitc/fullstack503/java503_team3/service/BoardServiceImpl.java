package bitc.fullstack503.java503_team3.service;


import bitc.fullstack503.java503_team3.dto.BoardDTO;
import bitc.fullstack503.java503_team3.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

//    목록
    @Override
    public List<BoardDTO> selectBoardList() {
        return boardMapper.selectBoardList();
    }

//    작성
    @Override
    public void insertBoard(BoardDTO board) {
        boardMapper.insertBoard(board);
    }

    //   상세
    @Override
    public BoardDTO selectBoardDetail(int idx) {
        boardMapper.updateHitCnt(idx);
        BoardDTO board= boardMapper.selectBoardDetail(idx);
        return board;
    }


}
