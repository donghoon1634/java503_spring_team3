package bitc.fullstack503.java503_team3.mapper;

import bitc.fullstack503.java503_team3.dto.UserlifeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    //    목록
    public List<UserlifeDTO> selectBoardList() ;
    //  조회수 증가
    public void updateHitCnt(int ulIdx) ;
    //  게시물 작성
    void insertBoard(UserlifeDTO ul);
    // 상세
    UserlifeDTO selectBoardDetail(int ulIdx);
    //   추천수 증가
    void plusLike(int ulIdx);

    //   추천수 조회;
    Object selectLikeCount(int ulIdx);

    // 게시물 수정
    void updateBoard(UserlifeDTO ul);

    //게시물 삭제
    void deleteBoard(int ulIdx);

    //    조회수 정렬
    List<UserlifeDTO> getPopularPosts(@Param("limit") int limit);


}
