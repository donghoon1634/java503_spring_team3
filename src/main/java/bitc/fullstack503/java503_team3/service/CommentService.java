package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    // 게시물의 댓글 목록을 가져오는 API
    List<CommentDTO> getCommentsByBoardIdx(int boardIdx);
    // 댓글을 추가하는 API
    void addComment(CommentDTO commentDTO);
    // 댓글 추천수를 증가시키는 API
    void incrementRecommend(int commentIdx);
}
