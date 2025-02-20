package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.userTradeCommentDTO;

import java.util.List;

public interface tradeUserCommentService {
    void qnaComment(userTradeCommentDTO utc);

    List<userTradeCommentDTO> getComment(int tradeBoardIdx);
}
