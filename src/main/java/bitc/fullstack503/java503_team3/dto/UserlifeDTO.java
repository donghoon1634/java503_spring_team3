package bitc.fullstack503.java503_team3.dto;


import lombok.Data;

import java.util.List;


@Data
public class UserlifeDTO {

    private int ulIdx;
    private String ulTitle;
    private String ulNickname;
    private String ulContents;
    private String ulPlace;
    private String ulCreateDate;
    private int ulHitCnt;
    private int ulLikes;
    private String ulCate;
    private String ulMemberId;
    private String ulUpdateDate;
    private List<UserlifeFileDTO> fileList;
    private int ulCommentCount;




}
