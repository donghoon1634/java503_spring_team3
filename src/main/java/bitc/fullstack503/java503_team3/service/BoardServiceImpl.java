package bitc.fullstack503.java503_team3.service;


import bitc.fullstack503.java503_team3.dto.UserlifeDTO;
import bitc.fullstack503.java503_team3.mapper.BoardMapper;
import bitc.fullstack503.java503_team3.mapper.UlCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private UlCommentMapper ulCommentMapper;

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
//추천수 중가
    @Override
    public Object plusLike(int ulIdx) {
        boardMapper.plusLike(ulIdx);

        return boardMapper.selectLikeCount(ulIdx);
    }

//    게시물 수정
    @Override
    public void updateBoard(UserlifeDTO ul) {
        boardMapper.updateBoard(ul);
    }

//    게시물 삭제
    @Override
    public void deleteBoard(int ulIdx) {

//        댓글 수 확인
        int count = ulCommentMapper.countComment(ulIdx);
        if (count > 0) {
            //댓글삭제
            ulCommentMapper.deleteComment(ulIdx);
        }
        boardMapper.deleteBoard(ulIdx);

    }

//    인기글 정렬
    @Override
    public List<UserlifeDTO> getPopularPosts(int limit) {
        return boardMapper.getPopularPosts(limit);

    }


}
