package bitc.fullstack503.java503_team3.dto;

import lombok.Data;

@Data
public class productEditDTO {
    private int idx;
    private int productEditBoardIdx;
    private String productEditUser;
    private String productEditTitle;
    private int productEditPrice;
    private String productEditContents;
    private String originalFileName;
    private String storedFileName;
    private int fileSize;

}
