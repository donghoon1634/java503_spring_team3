package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.UserlifeCommentDTO;

import java.util.List;

public interface UlCommentService {
    // 게시물의 댓글 목록을 가져오는 API
    List<UserlifeCommentDTO> getUlCommentByUlIdx(int ulIdx);

//    // 게시물의 댓글 목록을 가져오는 API
//    List<UserlifeCommentDTO> getCommentsByBoardIdx(int boardIdx);
//    // 댓글을 추가하는 API
//    void addComment(UserlifeCommentDTO ulCommentDTO);
//    // 댓글 추천수를 증가시키는 API
//    void incrementRecommend(int ulCommentIdx);
//    // 등록순 댓글 조회
//    List<UserlifeCommentDTO> getCommentsOldestFirst(int ulCommentUlIdx);
//    // 최신순 댓글 조회
//    List<UserlifeCommentDTO> getCommentsNewestFirst(int ulCommentUlIdx);

}
