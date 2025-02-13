package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.userMyPageDTO;
import bitc.fullstack503.java503_team3.mapper.MyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPageServiceImpl implements MyPageService {

@Autowired
private MyPageMapper myPageMapper;

    @Override
    public void updateMyPage(userMyPageDTO myPage) {
        myPageMapper.updateMyPage(myPage);
        
    }
}
