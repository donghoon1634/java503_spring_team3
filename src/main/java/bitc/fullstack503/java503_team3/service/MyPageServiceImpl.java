package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.userMyPageDTO;
import bitc.fullstack503.java503_team3.mapper.MyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageServiceImpl implements MyPageService {

@Autowired
private MyPageMapper myPageMapper;

    @Override
    public void updateMyPage(userMyPageDTO myPage) {
        myPageMapper.updateMyPage(myPage);
        
    }

    @Override
    public List<userMyPageDTO> selectMyPage(String myPageUser) {
        List<userMyPageDTO> myPageList = myPageMapper.selectMyPage(myPageUser);

        return myPageList;
    }
}
