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
    // 추천수 가져오기
    Object ulCommentLikeSelect(int ulCommentIdx);
    // 등록순 댓글 조회
    List<UserlifeCommentDTO> ulCommentAsc(int ulIdx);
    // 최신순 댓글 조회
    List<UserlifeCommentDTO> ulCommentDesc(int ulIdx);
    // 댓글을 추가하는 API
    int ulCommentInsert(UserlifeCommentDTO ulcDTO);

    void ulCommentdelet(int ulIdx);

    List<UserlifeCommentDTO> selectCommentsByPage(int ulIdx, int offset, int limit);
}
