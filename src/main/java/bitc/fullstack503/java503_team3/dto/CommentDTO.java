package bitc.fullstack503.java503_team3.dto;

import lombok.Data;

@Data
public class CommentDTO {

    private int commentIdx;
    private int boardIdx;
    private String nickname;
    private String location;
    private String createDate;
    private int recommendCount;
    private String commentContent;
}
