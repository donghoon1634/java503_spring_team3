package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.productEditDTO;

import java.util.List;

public interface TradeUpdateEditService {
    void updateTradeEdit(productEditDTO productEditDTO);

    List<productEditDTO> selectTradeEdit(int productEditBoardIdx);
}
