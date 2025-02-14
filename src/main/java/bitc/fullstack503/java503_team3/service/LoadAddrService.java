package bitc.fullstack503.java503_team3.service;
import java.util.List;
public interface LoadAddrService
{
  public List<String> selectLoadAddrGu () throws Exception;
  
  public List<String> selectLoadAddrDong (String loadAddrGu) throws Exception;
  
  public List<String> selectLoadAddrRo (String loadAddrGu, String loadAddrDong) throws Exception;
  
  public List<String> selectLoadAddrMainNum (String loadAddrGu, String loadAddrDong, String loadAddrRo) throws Exception;
  
  public List<String> selectLoadAddrSubNum (String loadAddrGu, String loadAddrDong, String loadAddrRo, String loadAddrMainNum) throws Exception;
}
