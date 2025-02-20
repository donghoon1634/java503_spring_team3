package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.productEditDTO;
import bitc.fullstack503.java503_team3.dto.userMyPageDTO;
import bitc.fullstack503.java503_team3.mapper.TradeUpdateEditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeUpdateEditServiceImpl implements TradeUpdateEditService {
    @Autowired
    TradeUpdateEditMapper tradeUpdateEditMapper;

    @Override
    public void updateTradeEdit(productEditDTO productEditDTO) {
        tradeUpdateEditMapper.updateTradeEdit(productEditDTO);
    }

    @Override
    public List<productEditDTO> selectTradeEdit(int productEditBoardIdx) {
        List<productEditDTO> editList = tradeUpdateEditMapper.selectTradeEdit(productEditBoardIdx);
        return editList;
    }
}