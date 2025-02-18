package bitc.fullstack503.java503_team3.service;
import bitc.fullstack503.java503_team3.dto.LoadAddrDTO;
import bitc.fullstack503.java503_team3.mapper.LoadAddrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoadAddrServiceImpl implements LoadAddrService
{
  @Autowired
  private LoadAddrMapper loadAddrMapper;
  
  @Override
  public List<String> selectLoadAddrGu () throws Exception
  {
    return loadAddrMapper.selectLoadAddrGu ();
  }
  
  @Override
  public List<String> selectLoadAddrDong (String loadAddrGu) throws Exception
  {
    return loadAddrMapper.selectLoadAddrDong (loadAddrGu);
  }
  
  @Override
  public List<String> selectLoadAddrRo (String loadAddrGu, String loadAddrDong) throws Exception
  {
    return loadAddrMapper.selectLoadAddrRo (loadAddrGu, loadAddrDong);
  }
  
  @Override
  public List<String> selectLoadAddrMainNum (String loadAddrGu, String loadAddrDong, String loadAddrRo) throws Exception
  {
    return loadAddrMapper.selectLoadAddrMainNum (loadAddrGu, loadAddrDong, loadAddrRo);
  }
  
  @Override
  public List<String> selectLoadAddrSubNum (String loadAddrGu, String loadAddrDong, String loadAddrRo, String loadAddrMainNum) throws Exception
  {
    return loadAddrMapper.selectLoadAddrSubNum (loadAddrGu, loadAddrDong, loadAddrRo, loadAddrMainNum);
  }
  
  @Override
  public String selectLoadAddr (String loadAddrGu, String loadAddrDong, String loadAddrRo, String loadAddrMainNum) throws Exception
  {
    return loadAddrMapper.selectLoadAddr (loadAddrGu, loadAddrDong, loadAddrRo, loadAddrMainNum);
  }
  
  @Override
  public String selectLoadAddrWithSubNum (String loadAddrGu, String loadAddrDong, String loadAddrRo, String loadAddrMainNum, String loadAddrSubNum) throws Exception
  {
    return loadAddrMapper.selectLoadAddrWithSubNum (loadAddrGu, loadAddrDong, loadAddrRo, loadAddrMainNum, loadAddrSubNum);
  }
  
  @Override
  public LoadAddrDTO selectLoadAddrIdx (String loadAddrIdx) throws Exception
  {
    return loadAddrMapper.selectLoadAddrIdx (loadAddrIdx);
  }
}
