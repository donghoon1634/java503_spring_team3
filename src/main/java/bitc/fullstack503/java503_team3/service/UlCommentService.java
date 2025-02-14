package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.UserlifeCommentDTO;

import java.util.List;

public interface UlCommentService {
    // 게시물의 댓글 목록을 가져오는 API
    List<UserlifeCommentDTO> getUlCommentByUlIdx(int ulIdx);
    // 추천수 증가 
    Object ulCommentLikeUpDate(int ulCommnetIdx);
    // 댓글 등록순
    List<UserlifeCommentDTO> ulCommentAsc(int ulIdx);
    // 댓글 추천수
    List<UserlifeCommentDTO> ulCommentDesc(int ulIdx);
    // 댓글을 추가하는 API
//    void addComment(UserlifeCommentDTO ulCommentDTO);

    void ulCommentInsert(UserlifeCommentDTO ulcDTO);
}
