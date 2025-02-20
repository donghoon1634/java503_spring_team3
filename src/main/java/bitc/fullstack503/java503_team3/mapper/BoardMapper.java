package bitc.fullstack503.java503_team3.mapper;

import bitc.fullstack503.java503_team3.dto.UserlifeDTO;
import bitc.fullstack503.java503_team3.dto.UserlifeFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    //    목록
    public List<UserlifeDTO> selectBoardList();
    // 지역구에 맞는 게시물 조회
    public List<UserlifeDTO> selectBoardListByLocation(String memberGu);

    //  조회수 증가
    public void updateHitCnt(int ulIdx);

    //    게시물 작성
    void insertBoard(UserlifeDTO ul);

    //    void insertBoard(List<UserlifeDTO> fileList) throws Exception;
    void insertFileList(@Param("fileList") List<UserlifeFileDTO> fileList, @Param("ulIdx") int ulIdx);

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

// 이미지 가져오기
    List<UserlifeFileDTO> selectFilesByUlIdx(int ulIdx);
    // 카테고리별 게시물 목록 페이지로 이동
    List<UserlifeDTO> getBoardByCategory(String ulCate);
    // 카테고리별 게시물 목록 페이지 - 로그인한 사람의 지역구 기준
    List<UserlifeDTO> getBoardByCategoryAndLocation(String ulCate, String memberGu);

    // 카테고리별- 인기글 목록 페이지로 이동
    List<UserlifeDTO> getBoardByCategoryPopular();

//    게시물 조회
//    UserlifeDTO getBoardById(int ulIdx);

    String getBoardAuthorId(int ulIdx);

    void deleteUlFile(int ulIdx);

    List<UserlifeDTO> getBoardByCategoryPopularAndLocation(String memberGu);
}
