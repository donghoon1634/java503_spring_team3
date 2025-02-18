package bitc.fullstack503.java503_team3.dto;


import lombok.Data;

import java.util.List;

@Data
public class UserlifeFileDTO {
    private int ulFileIdx;
    private int ulIdx;
    private String ulOriginalFileName;
    private String ulStoredFileName;
    private int ulFileSize;
    private List<UserlifeFileDTO> fileList;
}
