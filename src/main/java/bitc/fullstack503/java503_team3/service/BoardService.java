package bitc.fullstack503.java503_team3.service;


import bitc.fullstack503.java503_team3.dto.UserlifeDTO;

import java.util.List;

public interface BoardService {
//    게시물목록
    public List<UserlifeDTO> selectBoardList();
//게시물 작성
    void insertBoard(UserlifeDTO ul);

//게시물 상세
UserlifeDTO selectBoardDetail(int ulIdx);
}
