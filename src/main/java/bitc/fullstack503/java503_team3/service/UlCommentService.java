package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.UserlifeCommentDTO;

import java.util.List;

public interface UlCommentService {
    // 댓글 다시들고오기
    List<UserlifeCommentDTO> getUlCommentByUlIdx(int ulIdx);
    // 추천수 증가
    Object ulCommentLikeUpDate(int ulCommnetIdx);
    // 댓글 등록순
    List<UserlifeCommentDTO> ulCommentAsc(int ulIdx,int offset, int limit);
    // 댓글 추천수
    List<UserlifeCommentDTO> ulCommentDesc(int ulIdx,int offset, int limit);
    // 댓글을 추가하는 API
    void ulCommentInsert(UserlifeCommentDTO ulcDTO);
    // 댓글 삭제
//    void ulCommentdelet(int ulIdx);
    // 기본댓글 5개씩
    List<UserlifeCommentDTO> getCommentsByPage(int ulIdx, int offset, int limit);
    // 댓글 총 개수 반환
    int getTotalCommentCount(Long ulIdx);
    // 더보기
    List<UserlifeCommentDTO> getCommentsByPage(int ulIdx, int offset, int limit, String descOrAsc);
    // 댓글 수 조회
    int ulCommentCount(int ulIdx);
    // 게시물에 해당하는 댓글 개수 가져오기
    int getUlCommentCount(int ulIdx);

    // 댓글 작성자 조회
    String getCommentAuthor(int ulCommentIdx);

    // 댓글 삭제 (성공 여부 반환)
    boolean deleteComment(int ulCommentIdx);
}

