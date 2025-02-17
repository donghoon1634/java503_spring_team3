package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.userMyPageDTO;
import bitc.fullstack503.java503_team3.dto.userMyPageProductEditDTO;

import java.util.List;

public interface MyPageService {
    void updateMyPage(userMyPageDTO myPage);

    List<userMyPageDTO> selectMyPage(String myPageUser);

    List<userMyPageProductEditDTO> selectMyPageProduct(String myPageUser);

    List<userMyPageDTO> selectMyPage1(String myPageUser);

    List<userMyPageProductEditDTO> selectMyPageProduct1(String myPageUser);


}
