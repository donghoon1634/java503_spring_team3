package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.userMyPageDTO;

import java.util.List;

public interface MyPageService {
    void updateMyPage(userMyPageDTO myPage);

    List<userMyPageDTO> selectMyPage(String myPageUser);
}
