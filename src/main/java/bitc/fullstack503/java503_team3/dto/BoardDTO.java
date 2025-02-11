package bitc.fullstack503.java503_team3.dto;

import lombok.Data;

@Data
public class BoardDTO {

    private String id;
    private int idx;
    private String nickname;
    private String title;
    private String contents;
    private String createDate;
    private String place;
    private int hitCnt;
    private int likes;
    private String cate;
}
