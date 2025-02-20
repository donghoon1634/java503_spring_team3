package bitc.fullstack503.java503_team3.mapper;

import bitc.fullstack503.java503_team3.dto.userMyPageDTO;
import bitc.fullstack503.java503_team3.dto.userMyPageProductEditDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {
    void updateMyPage(userMyPageDTO myPage);

    List<userMyPageDTO> selectMyPage(String myPageUser);

    List<userMyPageProductEditDTO> selectMyPageProduct(String myPageUser);

    List<userMyPageDTO> selectMyPage1(String myPageUser);

    List<userMyPageProductEditDTO> selectMyPageProduct1(String myPageUser);
}
