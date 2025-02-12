package bitc.fullstack503.java503_team3.dto;

import lombok.Data;

@Data
public class userTradeCommentDTO {
    private int idx;
    private int tradeBoardIdx;
    private String tradeUser;
    private  String tradeCreateDate;
    private String tradeUserComment;
}
