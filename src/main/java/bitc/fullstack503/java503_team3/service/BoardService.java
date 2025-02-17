package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.UserlifeDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.List;

public interface BoardService {
    //    게시물목록
    public List<UserlifeDTO> selectBoardList();
    //게시물 작성
    void insertBoard(UserlifeDTO ul) throws  Exception;
    public void  insertBoard (UserlifeDTO ul, MultipartHttpServletRequest multipart) throws Exception;

    //게시물 상세
    UserlifeDTO selectBoardDetail(int ulIdx);
    //추천수 중가
    Object plusLike(int ulIdx);
    //    게시물 수정
    void updateBoard(UserlifeDTO ul);
    //    게시물 삭제
    void deleteBoard(int ulIdx);
    //    인기글 정렬
    List<UserlifeDTO> getPopularPosts(int limit);


}
