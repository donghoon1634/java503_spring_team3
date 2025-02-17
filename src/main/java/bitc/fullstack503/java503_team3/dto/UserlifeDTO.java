package bitc.fullstack503.java503_team3.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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




}
