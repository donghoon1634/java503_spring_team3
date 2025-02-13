package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.userTradeCommentDTO;
import bitc.fullstack503.java503_team3.mapper.UserTradeCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTradeCommentServiceImpl implements UserTradeCommentService {

    @Autowired
    private UserTradeCommentMapper userTradeCommentMapper;

    @Override
    public void qnaComment(userTradeCommentDTO utc) {
        userTradeCommentMapper.qnaComment(utc);
    }

    @Override
    public List<userTradeCommentDTO> getComment(int boardIdx) {
        return userTradeCommentMapper.getComment(boardIdx);
    }
}
