package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper boardMapper;
    @Override
    public void insertBoard(BoardDTO board) {
        boardMapper.insertBoard(board);
    }
}
