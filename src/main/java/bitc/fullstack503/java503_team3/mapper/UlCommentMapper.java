package bitc.fullstack503.java503_team3.mapper;

import bitc.fullstack503.java503_team3.dto.UserlifeCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UlCommentMapper {
    // 게시물의 댓글 목록을 가져오는 API
    List<UserlifeCommentDTO> getUlCommentByUlIdx(int ulIdx);
    // 추천수 증가
    void ulCommentLikeUpDate(int ulCommentIdx);
    // 추천수 불러오기
    int ulCommentLikeSelect(int ulCommentIdx);

//    // 게시물의 댓글 목록을 가져오는 API
//    List<UserlifeCommentDTO> getCommentsByBoardIdx(int ulCommentIdx);
//    // 댓글을 추가하는 API
//    void addComment(UserlifeCommentDTO ulCommentDTO);
//    // 댓글 추천수를 증가시키는 API
//    void incrementRecommend(int ulCommentIdx);
//    // 등록순 댓글 조회
//    List<UserlifeCommentDTO> getCommentsOldestFirst(int ulCommentIdx);
//    // 최신순 댓글 조회
//    List<UserlifeCommentDTO> getCommentsNewestFirst(int ulCommentIdx);


}
