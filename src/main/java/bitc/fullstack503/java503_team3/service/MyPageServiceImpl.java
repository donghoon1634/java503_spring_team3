package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.userMyPageDTO;
import bitc.fullstack503.java503_team3.dto.userMyPageProductEditDTO;
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

    @Override
    public List<userMyPageProductEditDTO> selectMyPageProduct(String myPageUser) {
        List<userMyPageProductEditDTO> myPageProduct = myPageMapper.selectMyPageProduct(myPageUser);
        if(myPageProduct.isEmpty()) {
            System.out.println("상품 데이터 없음");
        }

        return myPageProduct;
    }

    @Override
    public List<userMyPageDTO> selectMyPage1(String myPageUser) {
        List<userMyPageDTO> myPageList = myPageMapper.selectMyPage1(myPageUser);

        return myPageList;
    }

    @Override
    public List<userMyPageProductEditDTO> selectMyPageProduct1(String myPageUser) {
        List<userMyPageProductEditDTO> myPageProduct = myPageMapper.selectMyPageProduct1(myPageUser);
        if(myPageProduct.isEmpty()) {
            System.out.println("상품 데이터 없음");
        }

        return myPageProduct;
    }
}
