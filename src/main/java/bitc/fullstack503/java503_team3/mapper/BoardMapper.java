package bitc.fullstack503.java503_team3.mapper;

import bitc.fullstack503.java503_team3.dto.UserlifeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper {
//    목록
    public List<UserlifeDTO> selectBoardList() ;
//  조회수 증가
    public void updateHitCnt(int ulIdx) ;

//    게시물 작성
    void insertBoard(UserlifeDTO ul);

    // 상세
    UserlifeDTO selectBoardDetail(int ulIdx);


}
