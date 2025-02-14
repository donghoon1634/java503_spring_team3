package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.userTradeCommentDTO;
import bitc.fullstack503.java503_team3.mapper.tradeUserCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tradeUserCommentServiceImpl implements tradeUserCommentService {

    @Autowired
    private tradeUserCommentMapper tradeUserCommentMapper;

    @Override
    public void qnaComment(userTradeCommentDTO utc) {
        tradeUserCommentMapper.qnaComment(utc);
    }

    @Override
    public List<userTradeCommentDTO> getComment(int tradeBoardIdx) {
        return tradeUserCommentMapper.getComment(tradeBoardIdx);
    }
}
