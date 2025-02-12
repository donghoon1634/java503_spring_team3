package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.UserlifeCommentDTO;
import bitc.fullstack503.java503_team3.mapper.UlCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UlCommentServiceImpl implements UlCommentService {
    @Autowired
    private UlCommentMapper ulCommentMapper;

    // 게시물의 댓글 목록을 가져오는 API
    @Override
    public List<UserlifeCommentDTO> getUlCommentByUlIdx(int ulIdx) {
        List<UserlifeCommentDTO> comments = ulCommentMapper.getUlCommentByUlIdx(ulIdx);
        return comments;
    }

    // 추천수 증가
    @Override
    public void ulCommentLikeUpDate(int ulCommentIdx) {
        ulCommentMapper.ulCommentLikeUpDate(ulCommentIdx);
    }
    // 추천수 불러오기
    @Override
    public int ulCommentLikeSelect(int ulCommentIdx) {
        int result = ulCommentMapper.ulCommentLikeSelect(ulCommentIdx);
        return result;
    }


    //    // 게시물의 댓글 목록을 가져오는 API
//    @Override
//    public List<UserlifeCommentDTO> getCommentsByBoardIdx(int ulCommentIdx) {
//        List<UserlifeCommentDTO> comments = ulCommentMapper.getCommentsByBoardIdx(ulCommentIdx);
//        return comments;
//    }
//
//    // 댓글을 추가하는 API
//    @Override
//    public void addComment(UserlifeCommentDTO ulCommentDTO) {
//        ulCommentMapper.addComment(ulCommentDTO);
//    }
//
//    // 댓글 추천수를 증가시키는 API
//    @Override
//    public void incrementRecommend(int commentIdx) {
//        ulCommentMapper.incrementRecommend(commentIdx);
//    }



//
//    // 등록순 댓글 조회
//    @Override
//    public List<UserlifeCommentDTO> getCommentsOldestFirst(int ulCommentIdx) {
//        return ulCommentMapper.getCommentsOldestFirst(ulCommentIdx);
//    }
//
//    // 최신순 댓글 조회
//    @Override
//    public List<UserlifeCommentDTO> getCommentsNewestFirst(int ulCommentIdx) {
//        return ulCommentMapper.getCommentsNewestFirst(ulCommentIdx);
//    }
}
