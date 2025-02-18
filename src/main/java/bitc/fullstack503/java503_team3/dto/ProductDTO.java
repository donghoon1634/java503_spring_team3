package bitc.fullstack503.java503_team3.dto;

import lombok.Data;

@Data
public class ProductDTO {
  private int productNum; //제품번호
  private String productName; //제품명
  private int categoryNum; //물품 카테고리 넘버
  private String categoryName; // 카테고리 명칭
  private String productPrice; //가격
  private String productImg; //상품 이미지
  private int localGuNum; //거래 지역 번호
  private String localGuName; //거래 지역명
  private int memberIdx; //
}
