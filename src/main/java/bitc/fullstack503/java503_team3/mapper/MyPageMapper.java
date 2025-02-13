package bitc.fullstack503.java503_team3.mapper;

import bitc.fullstack503.java503_team3.dto.userMyPageDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {
    void updateMyPage(userMyPageDTO myPage);
}
