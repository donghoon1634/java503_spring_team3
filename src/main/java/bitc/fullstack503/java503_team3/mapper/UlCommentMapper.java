package bitc.fullstack503.java503_team3.mapper;


import bitc.fullstack503.java503_team3.dto.UserlifeCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UlCommentMapper {

//    댓글 수 확인
    int countComment(int ulIdx);

//    댓글삭제
    void deleteComment(int ulIdx);
}
