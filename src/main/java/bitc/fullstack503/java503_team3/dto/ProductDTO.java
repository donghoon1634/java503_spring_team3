package bitc.fullstack503.java503_team3.dto;

import lombok.Data;

@Data
public class ProductDTO {
  private int productNum;
  private String productName;
  private int categoryNum;
  private String productPrice;
  private String productImg;
  private int localGuNum;
  private String localGuName;
  private int memberIdx;
}
