package bitc.fullstack503.java503_team3.service;


import bitc.fullstack503.java503_team3.dto.UserlifeDTO;
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
    public List<UserlifeDTO> selectBoardList() {
        return boardMapper.selectBoardList();
    }

//    작성
    @Override
    public void insertBoard(UserlifeDTO ul) {
        boardMapper.insertBoard(ul);
    }

    //   상세
    @Override
    public UserlifeDTO selectBoardDetail(int ulIdx) {
        boardMapper.updateHitCnt(ulIdx);
        UserlifeDTO ul= boardMapper.selectBoardDetail(ulIdx);
        return ul;
    }


}
