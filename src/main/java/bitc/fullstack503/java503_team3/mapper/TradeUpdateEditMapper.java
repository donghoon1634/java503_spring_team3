package bitc.fullstack503.java503_team3.mapper;

import bitc.fullstack503.java503_team3.dto.productEditDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeUpdateEditMapper {
    void updateTradeEdit(productEditDTO productEditDTO);

    List<productEditDTO> selectTradeEdit(int productEditBoardIdx);
}
